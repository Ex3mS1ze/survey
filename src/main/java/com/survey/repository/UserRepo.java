package com.survey.repository;

import com.survey.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository <User,Long> {
    User findByEmail(String email);
    boolean existsUserByEmail(String email);
    Optional<User> findById(Long id);
}
