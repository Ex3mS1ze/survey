package com.survey.service;

import com.survey.entity.Diagnosis;
import com.survey.entity.Questionnaire;
import com.survey.entity.QuestionnaireType;
import com.survey.entity.assessment.AssessmentRange;
import com.survey.entity.assessment.AssessmentResult;
import com.survey.entity.assessment.CalculationModel;
import com.survey.entity.assessment.DirectAssessmentQuestionWeight;
import com.survey.repository.DiagnosisRepo;
import com.survey.repository.assessment.CalculationModelRepo;
import com.survey.repository.assessment.DirectAssessmentRangeRepo;
import com.survey.repository.assessment.DirectAssessmentResultRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Service
public class AssessmentService {
    private final CalculationModelRepo calculationModelRepo;
    private final DirectAssessmentRangeRepo directAssessmentRangeRepo;
    private final DirectAssessmentResultRepo directAssessmentResultRepo;

    private final Diagnosis defaultDiagnosis;

    public AssessmentService(DiagnosisRepo diagnosisRepo, CalculationModelRepo calculationModelRepo,
                             DirectAssessmentRangeRepo directAssessmentRangeRepo,
                             DirectAssessmentResultRepo directAssessmentResultRepo) {
        this.calculationModelRepo = calculationModelRepo;
        this.directAssessmentRangeRepo = directAssessmentRangeRepo;

        defaultDiagnosis = diagnosisRepo.findById(6L).get();
        this.directAssessmentResultRepo = directAssessmentResultRepo;
    }

    public Optional<Diagnosis> operateQuestionnaireDirect(Questionnaire questionnaire) {
        QuestionnaireType type = questionnaire.getType();
        Optional<CalculationModel> calculationModelOptional = calculationModelRepo.findByQuestionnaireType(type)
                                                                .stream()
                                                                .filter(model -> model.getName()
                                                                                      .equals("Непосредственная экспертная оценка"))
                                                                .findFirst();

        if (!calculationModelOptional.isPresent()) {
            log.warn("Нет модели непосредственного оценивания для опроса с типом {}", type.getName());
            return Optional.empty();
        }

        CalculationModel calculationModel = calculationModelOptional.get();
        List<DirectAssessmentQuestionWeight> weights = calculationModel.getWeights();

        BigDecimal sumOfAllWeights = BigDecimal.ZERO;
        for (DirectAssessmentQuestionWeight weight : weights) {
            sumOfAllWeights = sumOfAllWeights.add(weight.getWeight());
        }

        AtomicReference<BigDecimal> summary = new AtomicReference<>(BigDecimal.ZERO);
        for (DirectAssessmentQuestionWeight weight : weights) {
            BigDecimal finalSumOfAllWeights = sumOfAllWeights;
            questionnaire.getAnswers()
                         .stream()
                         .filter(answer -> answer.getQuestion().equals(weight.getQuestion()))
                         .findFirst()
                         .ifPresent(answer -> {
                             BigDecimal current = new BigDecimal(answer.getText());

                             BigDecimal currentNorm = current.multiply(weight.getNormCoefficient());
                             BigDecimal weightNorm = weight.getWeight().divide(finalSumOfAllWeights, 5, RoundingMode.HALF_DOWN);
                             BigDecimal toAdd = currentNorm.multiply(weightNorm);
                             summary.set(summary.get().add(toAdd));
                             log.debug("{} {} \n current={}, currentNorm={}, weight={}, weightNorm={}, toAdd={}", answer,
                                       answer.getQuestion(), current, currentNorm, weight.getWeight(), weightNorm, toAdd);
                         });
        }
        Diagnosis resultDiagnosis = getDiagnosis(calculationModel, summary);

        AssessmentResult assessmentResult = getDirectAssessmentResult(questionnaire, calculationModel, summary,
                                                                      resultDiagnosis);

        log.info("Результат обработки {}", assessmentResult);
        return Optional.ofNullable(resultDiagnosis);
    }

    private AssessmentResult getDirectAssessmentResult(Questionnaire questionnaire, CalculationModel calculationModel,
                                                      AtomicReference<BigDecimal> summary, Diagnosis resultDiagnosis) {
        AssessmentResult assessmentResult = new AssessmentResult();
        assessmentResult.setCalculationModel(calculationModel);
        assessmentResult.setConfirmed(false);
        assessmentResult.setDiagnosis(resultDiagnosis);
        assessmentResult.setSum(summary.get());
        assessmentResult.setQuestionnaire(questionnaire);
        directAssessmentResultRepo.save(assessmentResult);
        return assessmentResult;
    }

    private Diagnosis getDiagnosis(CalculationModel calculationModel, AtomicReference<BigDecimal> summary) {
        AssessmentRange assessmentRange = directAssessmentRangeRepo.findByCalculationModel(calculationModel);

        Diagnosis resultDiagnosis;
        if (summary.get().compareTo(assessmentRange.getMax()) > 0) {
            resultDiagnosis = assessmentRange.getDiagnosisMax();
        } else if (summary.get().compareTo(assessmentRange.getMin()) > 0) {
            resultDiagnosis = assessmentRange.getDiagnosisMin();
        } else {
            resultDiagnosis = defaultDiagnosis;
        }

        return resultDiagnosis;
    }
}
