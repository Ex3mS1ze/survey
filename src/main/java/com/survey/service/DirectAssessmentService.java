package com.survey.service;

import com.survey.entity.Diagnosis;
import com.survey.entity.Questionnaire;
import com.survey.entity.QuestionnaireType;
import com.survey.entity.assessment.*;
import com.survey.repository.DiagnosisRepo;
import com.survey.repository.assessment.CalculationModelRepo;
import com.survey.repository.assessment.DirectAssessmentRangeRepo;
import com.survey.repository.assessment.DirectAssessmentResultRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Service
public class DirectAssessmentService {
    private final DiagnosisRepo diagnosisRepo;
    private final CalculationModelRepo calculationModelRepo;
    private final DirectAssessmentRangeRepo directAssessmentRangeRepo;
    private final DirectAssessmentResultRepo directAssessmentResultRepo;

    private final Diagnosis defaultDiagnosis;

    public DirectAssessmentService(DiagnosisRepo diagnosisRepo, CalculationModelRepo calculationModelRepo,
                                   DirectAssessmentRangeRepo directAssessmentRangeRepo,
                                   DirectAssessmentResultRepo directAssessmentResultRepo) {
        this.diagnosisRepo = diagnosisRepo;
        this.calculationModelRepo = calculationModelRepo;
        this.directAssessmentRangeRepo = directAssessmentRangeRepo;

        defaultDiagnosis = diagnosisRepo.findById(6L).get();
        this.directAssessmentResultRepo = directAssessmentResultRepo;
    }

    public Diagnosis operateQuestionnaire(Questionnaire questionnaire) {
        QuestionnaireType type = questionnaire.getType();
        CalculationModel calculationModel = calculationModelRepo.findByQuestionnaireType(type)
                                                                .stream()
                                                                .filter(model -> model.getName()
                                                                                      .equals("Непосредственная экспертная оценка"))
                                                                .findFirst()
                                                                .get();
        List<DirectAssessmentQuestionWeight> weights = calculationModel.getWeights();

        AtomicReference<BigDecimal> summary = new AtomicReference<>(BigDecimal.ZERO);
        for (DirectAssessmentQuestionWeight weight : weights) {
            questionnaire.getAnswers()
                         .stream()
                         .filter(answer -> answer.getQuestion().equals(weight.getQuestion()))
                         .findFirst()
                         .ifPresent(answer -> {
                             BigDecimal min = new BigDecimal(answer.getQuestion().getOptions().get(0));
                             BigDecimal max = new BigDecimal(answer.getQuestion().getOptions().get(1));
                             BigDecimal current = new BigDecimal(answer.getText());

                             BigDecimal currentNorm = (current.subtract(min)).divide(max.subtract(min), 2, RoundingMode.CEILING);
                             BigDecimal toAdd = currentNorm.multiply(weight.getWeight());
                             summary.set(summary.get().add(toAdd));
                             log.debug("{} {} \nmin={}, max={}, current={}, currentNorm={}, weight={}, toAdd={}", answer,
                                       answer.getQuestion(), min, max, current, currentNorm, weight.getWeight(), toAdd);
                         });
        }
        Diagnosis resultDiagnosis = getDiagnosis(calculationModel, summary);

        AssessmentResult assessmentResult = getDirectAssessmentResult(questionnaire, calculationModel, summary,
                                                                      resultDiagnosis);

        log.info("Результат обработки {}", assessmentResult);
        return resultDiagnosis;
    }

    public AssessmentResult getDirectAssessmentResult(Questionnaire questionnaire, CalculationModel calculationModel,
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

    public Diagnosis getDiagnosis(CalculationModel calculationModel, AtomicReference<BigDecimal> summary) {
        DirectAssessmentRange assessmentRange = directAssessmentRangeRepo.findByCalculationModel(calculationModel);

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

    public Diagnosis operateQuestionnaireRanging(Questionnaire questionnaire) {
        QuestionnaireType type = questionnaire.getType();
        CalculationModel calculationModel = calculationModelRepo.findByQuestionnaireType(type)
                                                                .stream()
                                                                .filter(model -> model.getName()
                                                                                      .equals("Ранжированная экспертная оценка"))
                                                                .findFirst()
                                                                .get();;
        List<AssessmentQuestionRank> ranks = calculationModel.getRanks();

        int maxRankFrom = ranks.stream().map(AssessmentQuestionRank::getRangeFrom).max(Integer::compareTo).get();
        int maxRankTo = ranks.stream().map(AssessmentQuestionRank::getRangeTo).max(Integer::compareTo).get();
        int maxRank = Math.max(maxRankFrom, maxRankTo) + 1;

        AtomicReference<BigDecimal> summary = new AtomicReference<>(BigDecimal.ZERO);
        for (AssessmentQuestionRank rank : ranks) {
            questionnaire.getAnswers()
                         .stream()
                         .filter(answer -> answer.getQuestion().equals(rank.getQuestion()))
                         .findFirst()
                         .ifPresent(answer -> {
                             int from = maxRank - rank.getRangeFrom();
                             int to = maxRank - rank.getRangeTo();
                             final int diff = from - to + 1;

                             double score = 0;
                             do {
                                 score += to;
                                 to++;
                             } while (from >= to);
                             score /= diff;

                             BigDecimal current = new BigDecimal(answer.getText());
                             BigDecimal min = new BigDecimal(answer.getQuestion().getOptions().get(0));
                             BigDecimal max = new BigDecimal(answer.getQuestion().getOptions().get(1));
                             BigDecimal currentNorm = (current.subtract(min)).divide(max.subtract(min), 2, RoundingMode.CEILING);

                             BigDecimal toAdd = currentNorm.multiply(BigDecimal.valueOf(score));
                             summary.set(summary.get().add(toAdd));
                             log.debug("from={}, to={}, score={}, current={}, currentNorm={}, toAdd={}", from, to, score, current, currentNorm, toAdd);
                         });
        }

        Diagnosis resultDiagnosis = getDiagnosis(calculationModel, summary);

        AssessmentResult assessmentResult = getDirectAssessmentResult(questionnaire, calculationModel, summary,
                                                                      resultDiagnosis);

        log.info("Результат обработки {}", assessmentResult);
        return resultDiagnosis;
    }
}
