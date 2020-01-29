package com.survey.repository;

import com.survey.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuestionRepo extends JpaRepository<Question,Long> {
    Optional<Question> getById(Long id);
    List<Question> findAllByOrderById();
    @Query(value = "SELECT DISTINCT category, MIN(id) \nFROM questions \nGROUP BY category \nORDER BY MIN(id) ASC, category", nativeQuery = true)
    List<String> getAllQuestionCategories();

    List<Question> findByIdInOrderByInputType(List<Long> ids);
}
