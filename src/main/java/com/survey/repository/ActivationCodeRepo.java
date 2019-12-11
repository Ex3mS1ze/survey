package com.survey.repository;

import com.survey.entity.ActivationCode;
import com.survey.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActivationCodeRepo extends JpaRepository<ActivationCode, Long> {
    Optional<ActivationCode> findByCode(String code);

    boolean existsByUser(User user);

    Optional<ActivationCode> findByUser(User user);

    void deleteByUser(User user);
}
