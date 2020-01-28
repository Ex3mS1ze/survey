package com.survey.entity;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class QuestionnaireTest {
    private Questionnaire questionnaire;

    @Before
    public void setUp() throws Exception {
        questionnaire = new Questionnaire();
        questionnaire.setId(1L);
        questionnaire.setDate(LocalDateTime.now());
        questionnaire.setProcessed(false);
        Answer answer1 = new Answer();
        Answer answer2 = new Answer();
        answer1.setText("test");
        answer2.setTextList(new ArrayList<>(Arrays.asList("1", "2")));
        questionnaire.setAnswers(new ArrayList<>(Arrays.asList(answer1, answer2)));

    }

    @Test
    public void isAnyAnswerEmptyTest() {
        assertFalse(questionnaire.isAnyAnswerEmpty());
        questionnaire.getAnswers().get(0).setText("");
        assertTrue(questionnaire.isAnyAnswerEmpty());
    }
}