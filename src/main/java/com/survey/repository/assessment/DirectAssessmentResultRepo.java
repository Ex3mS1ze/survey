package com.survey.repository.assessment;

import com.survey.entity.assessment.AssessmentResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectAssessmentResultRepo extends JpaRepository<AssessmentResult, Long> {
}
