package com.main.repositoty;

import com.main.entity.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionnaireRepo extends JpaRepository<Questionnaire, Long> {
}
