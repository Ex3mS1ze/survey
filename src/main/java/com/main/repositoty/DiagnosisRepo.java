package com.main.repositoty;

import com.main.entity.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosisRepo extends JpaRepository<Diagnosis, Long> {
}
