package com.main.repository;

import com.main.entity.Doctor;
import com.main.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepo extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByUser(User user);
}
