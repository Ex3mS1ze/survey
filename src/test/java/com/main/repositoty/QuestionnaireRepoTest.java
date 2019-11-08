package com.main.repositoty;

import com.main.entity.Questionnaire;
import com.main.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.Assert.assertNotNull;
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class QuestionnaireRepoTest {
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
}