package com.survey.repository.assessment;

import com.survey.entity.assessment.CalculationModel;
import com.survey.entity.assessment.AssessmentRange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectAssessmentRangeRepo extends JpaRepository<AssessmentRange, Long> {
    AssessmentRange findByCalculationModel(CalculationModel model);
}
