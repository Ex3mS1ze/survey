package com.survey.repository.assessment;

import com.survey.entity.QuestionnaireType;
import com.survey.entity.assessment.CalculationModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculationModelRepo extends JpaRepository<CalculationModel, Long> {
    CalculationModel findByQuestionnaireType(QuestionnaireType type);
}
