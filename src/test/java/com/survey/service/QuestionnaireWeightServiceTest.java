package com.survey.service;

import com.survey.entity.*;
import com.survey.repository.QuestionRepo;
import com.survey.repository.QuestionnaireRepo;
import com.survey.repository.QuestionnaireTypeRepo;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class QuestionnaireWeightServiceTest {
    @Autowired
    private QuestionWeightService questionWeightService;
    @Autowired
    private QuestionnaireTypeRepo questionnaireTypeRepo;
    @Autowired
    private QuestionnaireRepo questionnaireRepo;

    private QuestionnaireType questionnaireType;
    private Questionnaire questionnaire;

    @Before
    public void setUp() throws Exception {
        questionnaireType = questionnaireTypeRepo.getOne(1L);
        questionnaire = questionnaireRepo.getOne(164L);
    }

    //private method
    @Ignore
    @Test
    public void getMapOfQuestionWeights_test() {
        /*Map<Diagnosis, List<QuestionWeight>> result = questionWeightService.getMapOfQuestionWeights(
                questionnaireType);
        assertNotNull(result);
        assertTrue(result.size() > 0);*/
    }

    @Test
    public void operateQuestionnaire_test() {
        List<ScoreQuestionnaireResult> result = questionWeightService.operateQuestionnaire(
                questionnaire);
        assertNotNull(result);
        assertTrue(result.size() > 0);
    }
}