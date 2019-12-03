package com.main.repository;

import com.main.entity.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiagnosisRepo extends JpaRepository<Diagnosis, Long> {
    @Query(value = "select diagnosis.id from Diagnosis diagnosis")
    List<Long> getAllIds();
}
