package com.survey.repository;

import com.survey.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepo extends JpaRepository<Question,Long> {
    Question getById(Long id);
    List<Question> findAllByOrderById();
    @Query(value = "SELECT DISTINCT category, MIN(id) \n" + "FROM questions \n" + "GROUP BY category \n" + "ORDER BY MIN(id) ASC, category", nativeQuery = true)
    List<String> getAllQuestionCategories();
}