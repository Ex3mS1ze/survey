package com.survey.repository.assessment;

import com.survey.entity.QuestionnaireType;
import com.survey.entity.assessment.CalculationModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalculationModelRepo extends JpaRepository<CalculationModel, Long> {
    List<CalculationModel> findByQuestionnaireType(QuestionnaireType type);
}
