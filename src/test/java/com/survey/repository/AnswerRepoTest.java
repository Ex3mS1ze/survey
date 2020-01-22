package com.survey.repository;

import com.survey.entity.Answer;
import com.survey.entity.Question;
import com.survey.entity.Questionnaire;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
//@Transactional
public class AnswerRepoTest {
    private static final Logger LOGGER = LogManager.getLogger(AnswerRepoTest.class.getName());
    @Autowired
    private AnswerRepo answerRepo;
    @Autowired
    private QuestionRepo questionRepo;
    @Autowired
    private QuestionnaireRepo questionnaireRepo;
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void saveAnswer() {
        Answer answer = new Answer();
        Question question = questionRepo.findById(1L).get();
        answer.setQuestion(question);
        answer.setText("22");
        Questionnaire questionnaire = questionnaireRepo.findById(2L).get();
        answer.setQuestionnaire(questionnaire);
        Answer savedAnswer = answerRepo.save(answer);
        assertNotNull(answerRepo.findById(savedAnswer.getId()));
    }

    @Test
    public void addQuestion() {
        Question question = new Question();
        question.setId(3L);
        question.setText("Vopros");
        question.setInputType("text");
        Question savedQuestion = questionRepo.save(question);
        assertNotNull(questionRepo.findById(savedQuestion.getId()).get());
    }

    @Test
    public void getQuestionByQuestionIdAndQuestionnaireIdTest() {
        Answer answer = answerRepo.getByQuestionIdAndQuestionnaireId(1L, 172L);
        assertNotNull(answer);
    }
}