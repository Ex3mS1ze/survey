package com.survey.repository;

import com.survey.entity.QuestionnaireType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QuestionnaireTypeRepo extends JpaRepository<QuestionnaireType, Long> {
    Optional<QuestionnaireType> getByName(String name);

    @Query("select questionnaireTypes.name from QuestionnaireType questionnaireTypes")
    List<String> getAllTypesNames();
    //TODO make optional
    @Query(value = "select questions.id from questionnaires_questions left join questions on questionnaires_questions.question_id = questions.id left join questionnaire_types on questionnaires_questions.type_id = questionnaire_types.id where questionnaires_questions.type_id = :type_id order by question_order", nativeQuery = true)
    List<Long> getOrderedQuestionsIds(@Param("type_id") Long id);


}
