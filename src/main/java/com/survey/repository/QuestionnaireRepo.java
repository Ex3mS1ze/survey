package com.survey.repository;

import com.survey.entity.Questionnaire;
import com.survey.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionnaireRepo extends JpaRepository<Questionnaire, Long> {
    @Query(value = "SELECT nextval(pg_get_serial_sequence('questionnaires', 'id'))", nativeQuery = true)
    Long getNextId();
    List<Questionnaire> findAllByUserOrderByDate(User user);
    void deleteById(Long id);


}
