package com.main.repository;

import com.main.entity.Patient;
import com.main.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import static org.junit.Assert.assertNotNull;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PatientRepoTest {
    @Autowired
    private PatientRepo patientRepo;
    @Autowired
    private UserRepo userRepo;
    private Patient patient;
    @Before
    public void setUp() throws Exception {
        patient = new Patient();
    }

    @Test
    public void savePatient() {
        User user = userRepo.findById(2L).orElseThrow(() -> new EntityNotFoundException("id=2"));
        patient.setUser(user);
        assertNotNull(patientRepo.findByUser(user).get().getUser());
    }
}