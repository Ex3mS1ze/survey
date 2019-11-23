package com.main.repository;

import com.main.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepo extends JpaRepository<Question,Long> {
    List<Question> findAllByOrderById();
}
