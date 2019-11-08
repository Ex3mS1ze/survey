package com.main.repositoty;

import com.main.entity.Answer;
import com.main.entity.Question;
import com.main.entity.Questionnaire;
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
        question.setId(4L);
        question.setText("Vopros");
        question.setInputType("text");
        Question savedQuestion = questionRepo.save(question);
        assertNotNull(questionRepo.findById(savedQuestion.getId()).get());
    }
}