package com.survey.service;

import com.survey.entity.Answer;
import com.survey.entity.Question;
import com.survey.entity.Questionnaire;
import com.survey.entity.QuestionnaireType;
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

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class QuestionnaireServiceTest {
    @Autowired
    private QuestionnaireService questionnaireService;
    @Autowired
    private QuestionRepo questionRepo;
    @Autowired
    private QuestionnaireRepo questionnaireRepo;
    @Autowired
    private QuestionnaireTypeRepo questionnaireTypeRepo;
    private Questionnaire questionnaire;

    @Before
    public void setUp() throws Exception {
        questionnaire = new Questionnaire();

        List<Question> questions = questionRepo.findAll(PageRequest.of(0, 10)).get().collect(Collectors.toList());
        ArrayList<Answer> answers = new ArrayList<>();
        for (Question question : questions) {
            Answer answer = new Answer();
            answer.setText(String.valueOf(answers.size()));
            answer.setQuestion(question);
            answers.add(answer);
        }
        questionnaire.setAnswers(answers);

    }

    @Test
    public void getQuestionnaireTypeByTypeName() {
        QuestionnaireType gastroType = questionnaireService.getQuestionnaireTypeByTypeName(
                QuestionnaireService.GASTRO_TYPE);
        assertEquals(QuestionnaireService.GASTRO_TYPE, gastroType.getName());
        assertTrue(gastroType.getQuestions().size() > 0);

        QuestionnaireType cardioType = questionnaireService.getQuestionnaireTypeByTypeName(
                QuestionnaireService.CARDIO_TYPE);
        assertEquals(QuestionnaireService.CARDIO_TYPE, cardioType.getName());
        assertTrue(cardioType.getQuestions().size() > 0);
    }

    @Test
    public void getOrderedQuestionsByTypeId() {
        List<Question> list1 = questionnaireService.getOrderedQuestionsByTypeId(1L);
        List<Question> list2 = questionnaireService.getOrderedQuestionsByTypeId(2L);
        assertTrue(list1 != null && list2 != null);
        List<Question> list11 = questionnaireService.getOrderedQuestionsByTypeId(1L);
        List<Question> list22 = questionnaireService.getOrderedQuestionsByTypeId(2L);
        assertTrue(list1.equals(list11) && list2.equals(list22));
    }

    @WithUserDetails(value = "admin@mail.ru", userDetailsServiceBeanName = "myUserDetailsService")
    @Test
    public void saveNewQuestionnaire() {
        long newId = questionnaireService.saveNewQuestionnaire(questionnaire);
        Optional<Questionnaire> savedQuestionnaire = questionnaireRepo.findById(newId);
        assertTrue(savedQuestionnaire.isPresent());
        assertEquals(savedQuestionnaire.get().getAnswers(), questionnaire.getAnswers());
    }

    @Test(expected = NoSuchElementException.class)
    public void saveExistedQuestionnaire_exception() {
        questionnaireService.saveExistedQuestionnaire(new Questionnaire(), 0L);
    }

    @Test
    public void saveExistedQuestionnaire() {
        Questionnaire questionnaire = questionnaireRepo.findAll(PageRequest.of(0, 1))
                                                       .get()
                                                       .collect(Collectors.toList())
                                                       .get(0);
        questionnaireService.saveExistedQuestionnaire(questionnaire, questionnaire.getId());
    }

    @Test
    @Ignore
    public void saveExistedProcessedQuestionnaire() {
        questionnaireRepo.findAllByProcessed(false, PageRequest.of(0, 1)).get().get(0);

    }

    @WithUserDetails(value = "admin@mail.ru", userDetailsServiceBeanName = "myUserDetailsService")
    @Test
    public void getAllUsersQuestionnaires() {
        List<Questionnaire> allUsersQuestionnaires = questionnaireService.getAllUsersQuestionnaires();
        assertNotNull(allUsersQuestionnaires);
        assertTrue(allUsersQuestionnaires.size() > 0);
    }

    @Test
    public void getAllQuestionnaires() {
        List<Questionnaire> allQuestionnaires = questionnaireService.getAllQuestionnaires();
        assertTrue(allQuestionnaires.size() > 0);
    }

    @Test(expected = Exception.class)
    public void deleteQuestionnaireById() {
        Questionnaire questionnaire = questionnaireRepo.findAll(PageRequest.of(0, 1))
                                                       .get()
                                                       .collect(Collectors.toList())
                                                       .get(0);
        questionnaireService.deleteQuestionnaireById(questionnaire.getId(), true);
        assertNull(questionnaireRepo.getOne(questionnaire.getId()));
    }

    @Test(expected = NoSuchElementException.class)
    public void findQuestionnaireById_exception() {
        questionnaireService.findQuestionnaireById(0L, false);
    }

    @Test
    // useless test
    public void findQuestionnaireById() {
        QuestionnaireService mock = Mockito.mock(QuestionnaireService.class);
        Mockito.when(mock.findQuestionnaireById(Mockito.anyLong(), false)).thenReturn(new Questionnaire());
        Questionnaire questionnaire = mock.findQuestionnaireById(1L,false);
        assertNotNull(questionnaire);
    }

    @Test
    //TODO test if calculate
    public void operateQuestionnaireAnswers() {
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setAnswers(new ArrayList<>());
        assertFalse(questionnaireService.operateQuestionnaireAnswers(questionnaire, true, 1L));

    }

    @Test
    public void getAllQuestionCategories() {
        List<String> categories = questionnaireService.getAllQuestionCategories();
        assertTrue(categories.size() > 0);
    }

    @Test
    public void getOrderedAnswersByQuestionsAndQuestionnaireId() {
        for (int i = 0; i < 5; i++) {
            Questionnaire questionnaire = questionnaireRepo.findAll(PageRequest.of(i, 1))
                                                           .get()
                                                           .collect(Collectors.toList())
                                                           .get(0);
            List<Question> type = questionnaireService.getOrderedQuestionsByTypeId(questionnaire.getType().getId());
            List<Answer> orderedAnswers1 = questionnaireService.getOrderedAnswers(type, questionnaire.getId());
            List<Answer> orderedAnswers2 = questionnaireService.getOrderedAnswers(type, questionnaire.getId());
            assertEquals(orderedAnswers1, orderedAnswers2);
        }
    }

    @Test
    public void getNewQuestionnaireByTypeName() {
        Questionnaire questionnaire1 = questionnaireService.getNewQuestionnaireByTypeName(
                QuestionnaireService.CARDIO_TYPE);
        Questionnaire questionnaire2 = questionnaireService.getNewQuestionnaireByTypeName(
                QuestionnaireService.GASTRO_TYPE);
        assertEquals(questionnaire1.getType().getName(), QuestionnaireService.CARDIO_TYPE);
        assertEquals(questionnaire2.getType().getName(), QuestionnaireService.GASTRO_TYPE);
    }

    @Test
    @Ignore
    public void mineTest() {
        List<Long> questionsIds = questionnaireTypeRepo.getOrderedQuestionsIds(1L);
        System.out.println("===========================================");
        System.out.println(questionsIds);
        System.out.println("===========================================");
        List<Question> list = questionRepo.findByIdInOrderByInputType(questionsIds);
        System.out.println("===========================================");
        System.out.println(list.stream().map(Question::getId).collect(Collectors.toList()));
        System.out.println("===========================================");
    }
}