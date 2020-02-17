package com.survey.repository;

import com.survey.entity.QuestionWeight;
import com.survey.entity.QuestionnaireType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class QuestionWeightRepoTest {
    @Autowired
    private QuestionWeightRepo questionWeightRepo;

    private QuestionnaireType questionnaireType;

    @Before
    public void setUp() throws Exception {
        questionnaireType = new QuestionnaireType();
        questionnaireType.setId(1L);
    }

    @Test
    public void find_by_questionnaire_type_test() {
        List<QuestionWeight> allByQuestionnaireType = questionWeightRepo.findAllByQuestionnaireType(questionnaireType);
        assertNotNull(allByQuestionnaireType);
        assertTrue(allByQuestionnaireType.size() > 0);
    }
}
