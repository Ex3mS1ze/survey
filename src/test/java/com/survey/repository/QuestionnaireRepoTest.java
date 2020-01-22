package com.survey.repository;

import com.survey.entity.Questionnaire;
import com.survey.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-dev.properties")
@Transactional
public class QuestionnaireRepoTest {
    @Autowired
    private QuestionnaireTypeRepo questionnaireTypeRepo;
    @Autowired
    private QuestionnaireRepo questionnaireRepo;
    @Autowired
    private UserRepo userRepo;
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void addQuestionnaire() {
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setDate(LocalDateTime.now());
        questionnaire.setProcessed(false);
        User user = userRepo.findById(2L).get();
        questionnaire.setUser(user);
        questionnaireRepo.save(questionnaire);
        assertNotNull(questionnaireRepo.findById(questionnaire.getId()).get().getUser());
    }

    @Test
    public void testGetAllTypesNames() {
        List<String> allTypesNames = questionnaireTypeRepo.getAllTypesNames();
        assertTrue(allTypesNames.size() >= 2);
    }

    @Test
    public void testOrderedQuestionsIds() {
        List<Long> orderedQuestionsIds = questionnaireTypeRepo.getOrderedQuestionsIds(2L);
        assertTrue(orderedQuestionsIds != null && orderedQuestionsIds.size() > 0);
    }
}