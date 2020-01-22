package com.survey.repository;

import com.survey.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepo extends JpaRepository<Answer, Long> {
    Answer getByQuestionIdAndQuestionnaireId(Long question_id, Long questionnaire_id);
}
