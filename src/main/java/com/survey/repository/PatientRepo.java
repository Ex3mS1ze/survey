package com.survey.repository;

import com.survey.entity.Patient;
import com.survey.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PatientRepo extends JpaRepository<Patient, Long> {
    Optional<Patient> findByUser(User user);
    Optional<Patient> findById(Long id);
    Optional<List<Patient>> findByDoctorNull(Pageable pageable);
    Optional<List<Patient>> findByDoctorNotNull(Pageable pageable);
    Optional<List<Patient>> findByDiagnosisNotNull(Pageable pageable);

}
