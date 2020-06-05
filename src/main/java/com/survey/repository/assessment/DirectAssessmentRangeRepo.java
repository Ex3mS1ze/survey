package com.survey.repository.assessment;

import com.survey.entity.assessment.CalculationModel;
import com.survey.entity.assessment.DirectAssessmentRange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectAssessmentRangeRepo extends JpaRepository<DirectAssessmentRange, Long> {
    DirectAssessmentRange findByCalculationModel(CalculationModel model);
}
