package com.survey.repository;

import com.survey.entity.Diagnosis;
import com.survey.entity.QuestionWeight;
import com.survey.entity.QuestionnaireType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface QuestionWeightRepo extends JpaRepository<QuestionWeight, Long> {
    Optional<QuestionWeight> findById(Long id);

    List<QuestionWeight> findAllByQuestionnaireType(QuestionnaireType questionnaireType);

}
