package com.survey.repository;

import com.survey.entity.ScoreQuestionnaireResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreQuestionnaireRepo extends JpaRepository<ScoreQuestionnaireResult, Long> {
}
