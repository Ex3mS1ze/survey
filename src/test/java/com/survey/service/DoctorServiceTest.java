package com.survey.service;

import com.survey.entity.Diagnosis;
import com.survey.entity.Doctor;
import com.survey.entity.Patient;
import com.survey.repository.DiagnosisRepo;
import com.survey.repository.DoctorRepo;
import com.survey.repository.PatientRepo;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class DoctorServiceTest {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientRepo patientRepo;
    @Autowired
    private DoctorRepo doctorRepo;
    @Autowired
    DiagnosisRepo diagnosisRepo;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getAllPatients() {
        assertNotNull(doctorService.getAllPatients());
    }

    @Test
    public void getPatientById() {
        assertNotNull(doctorService.getPatientById(1L));
    }

    @WithUserDetails(value = "admin@mail.ru", userDetailsServiceBeanName = "myUserDetailsService")
    @Test
    public void getDoctorFromCurrentPrincipal() {
        assertNotNull(doctorService.getDoctorFromCurrentPrincipal());
    }

    @WithUserDetails(value = "admin@mail.ru", userDetailsServiceBeanName = "myUserDetailsService")
    @Test
    @Ignore
    public void attachPatient() {
        List<Patient> patientsWithOutDoctor = patientRepo.findByDoctorNull(PageRequest.of(0, 1)).get();
        doctorService.attachPatient(patientsWithOutDoctor.get(0));
        Patient patient = patientRepo.findById(patientsWithOutDoctor.get(0).getId()).get();
        assertNotNull(patient.getDoctor());
    }

    @WithUserDetails(value = "admin@mail.ru", userDetailsServiceBeanName = "myUserDetailsService")
    @Test
    @Ignore
    public void detachPatient() {
        List<Patient> patientsWithDoctor = patientRepo.findByDoctorNotNull(PageRequest.of(0, 1)).get();
        doctorService.detachPatient(patientsWithDoctor.get(0));
        Patient patient = patientRepo.findById(patientsWithDoctor.get(0).getId()).get();
        assertNull(patient.getDoctor());
    }

    @Test
    public void getDoctorById() {
        Doctor doctor = doctorRepo.findAll(PageRequest.of(0, 1)).get().collect(Collectors.toList()).get(0);

        Doctor doctorById = doctorService.getDoctorById(doctor.getId());
        assertEquals(doctor, doctorById);
    }

    @Test
    public void getPatientByUser() {
    }

    @Test
    public void getAllDiagnoses() {
        List<Diagnosis> allDiagnoses = doctorService.getAllDiagnoses();
        assertTrue(allDiagnoses.size() >=1);
    }

    @Test
    public void changeDiagnosis() {
        Patient patientsWithDiagnosis = patientRepo.findByDiagnosisNotNull(PageRequest.of(0, 1)).get().get(0);

        Diagnosis oldDiagnosis = patientsWithDiagnosis.getDiagnosis();
        patientsWithDiagnosis.setDiagnosis(null);
        doctorService.changeDiagnosis(patientsWithDiagnosis);
        Patient patientAfterSave = patientRepo.findById(patientsWithDiagnosis.getId()).get();
        assertNotEquals(oldDiagnosis, patientAfterSave.getDiagnosis());
    }

    @Test
    public void getAllDoctors() {
        List<Doctor> allDoctors = doctorService.getAllDoctors();
        assertTrue(allDoctors.size() >= 1);
    }
}