package com.survey.repository.assessment;

import com.survey.entity.assessment.AssessmentQuestionRank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectAssessmentQuestionRankRepo extends JpaRepository<AssessmentQuestionRank, Long> {
}
