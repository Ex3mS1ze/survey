package com.survey.service;

import com.survey.entity.*;
import com.survey.repository.QuestionWeightRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class QuestionWeightService {
    private static final Logger LOGGER = LogManager.getLogger(QuestionWeightService.class.getName());

    private final QuestionWeightRepo questionWeightRepo;

    @Autowired
    public QuestionWeightService(QuestionWeightRepo questionWeightRepo) {
        this.questionWeightRepo = questionWeightRepo;
    }

    private ScoreQuestionnaireResult calculateTest(Questionnaire questionnaire, List<QuestionWeight> questionsWeights) {
        BigDecimal resultScore = BigDecimal.ZERO;
        //Calculation may use part of questions & answers
        List<Long> usableQuestionsIds = questionsWeights.stream()
                                                        .map(questionWeight -> questionWeight.getQuestion().getId())
                                                        .collect(Collectors.toList());
        List<Answer> usableAnswers = questionnaire.getAnswers()
                                                    .stream()
                                                    .filter(answer -> usableQuestionsIds.contains(
                                                            answer.getQuestion().getId()))
                                                    .collect(Collectors.toList());
        for (Answer answer : usableAnswers) {
            BigDecimal toAdd;
            String inputType = answer.getQuestion().getInputType();
            List<QuestionWeight> exactQuestionWeight = questionsWeights.stream()
                                                                       .filter(qW -> qW.getQuestion()
                                                                                       .equals(answer.getQuestion()))
                                                                       .collect(Collectors.toList());

            if ("number".equals(inputType)) {
                toAdd = operateNumberAnswer(answer, exactQuestionWeight);
                resultScore = resultScore.add(toAdd);
            } else if ("radio".equals(inputType)) {
                toAdd = operateRadioAnswer(answer, exactQuestionWeight);
                resultScore = resultScore.add(toAdd);
            } else if ("checkbox".equals(inputType)) {
                toAdd = operateCheckboxAnswer(answer, exactQuestionWeight);
                resultScore = resultScore.add(toAdd);
            }
        }

        ScoreQuestionnaireResult scoreQuestionnaireResult = new ScoreQuestionnaireResult();
        scoreQuestionnaireResult.setDiagnosis(questionsWeights.get(0).getDiagnosis());
        scoreQuestionnaireResult.setQuestionnaire(questionnaire);
        scoreQuestionnaireResult.setScore(resultScore);
        return scoreQuestionnaireResult;
    }

    private BigDecimal operateNumberAnswer(Answer answer, List<QuestionWeight> exactQuestionWeight) {
        BigDecimal answerNumber = new BigDecimal(answer.getText().trim());
        BigDecimal toAdd = BigDecimal.ZERO;

        forLoop:
        for (QuestionWeight weight : exactQuestionWeight) {
            switch (weight.getBoundType()) {
                case "in":
                    if (answerNumber.compareTo(weight.getLowBound()) >= 0 &&
                        answerNumber.compareTo(weight.getUpBound()) < 0) {
                        toAdd = toAdd.add(weight.getLtWeight());
                        break forLoop;
                    }
                    break;
                case "out":
                    if (answerNumber.compareTo(weight.getLowBound()) <= 0) {
                        toAdd = weight.getLtWeight();
                    } else if (answerNumber.compareTo(weight.getUpBound()) > 0) {
                        toAdd = weight.getGtWeight();
                    }
                    break forLoop;
            }
        }
        return toAdd;
    }

    private BigDecimal operateCheckboxAnswer(Answer answer, List<QuestionWeight> exactQuestionWeight) {
        List<String> answerList = answer.getTextList();
        BigDecimal toAdd = BigDecimal.ZERO;

        for (int i = 0; i < answerList.size(); i++) {
            String textAnswerFromList = answerList.get(i);
            if (!"null".equals(textAnswerFromList)) {
                toAdd = toAdd.add(new BigDecimal(exactQuestionWeight.get(0).getWeightsList().get(i)));
            }
        }
        return toAdd;
    }

    private BigDecimal operateRadioAnswer(Answer answer, List<QuestionWeight> exactQuestionWeight) {
        int answerIndex = answer.getQuestion().getOptions().indexOf(answer.getText());
        return new BigDecimal(exactQuestionWeight.get(0).getWeightsList().get(answerIndex));
    }

    private Map<Diagnosis, List<QuestionWeight>> getMapOfQuestionWeights(QuestionnaireType questionnaireType) {
        List<QuestionWeight> allWeightsForQuestionnaireType = questionWeightRepo.findAllByQuestionnaireType(
                questionnaireType);
        return allWeightsForQuestionnaireType.stream().collect(Collectors.groupingBy(QuestionWeight::getDiagnosis));
    }

    public List<ScoreQuestionnaireResult> operateQuestionnaire(Questionnaire questionnaire) {
        Map<Diagnosis, List<QuestionWeight>> mapOfQuestionWeights = getMapOfQuestionWeights(
                questionnaire.getType());
        List<ScoreQuestionnaireResult> resultsForAllDiagnoses = new ArrayList<>();

        for (Map.Entry<Diagnosis, List<QuestionWeight>> entry : mapOfQuestionWeights.entrySet()) {
            resultsForAllDiagnoses.add(calculateTest(questionnaire, Collections.unmodifiableList(entry.getValue())));
        }

        return resultsForAllDiagnoses;
    }
}
