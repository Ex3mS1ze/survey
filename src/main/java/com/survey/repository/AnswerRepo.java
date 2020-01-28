package com.survey.repository;

import com.survey.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnswerRepo extends JpaRepository<Answer, Long> {
    Optional<Answer> getByQuestionIdAndQuestionnaireId(Long question_id, Long questionnaire_id);
}
