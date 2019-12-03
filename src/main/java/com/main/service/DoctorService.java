package com.main.service;

import com.main.entity.Diagnosis;
import com.main.entity.Doctor;
import com.main.entity.Patient;
import com.main.entity.User;
import com.main.repository.DiagnosisRepo;
import com.main.repository.DoctorRepo;
import com.main.repository.PatientRepo;
import com.main.repository.UserRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    private static final Logger LOGGER = LogManager.getLogger(DoctorService.class.getName());

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private DoctorRepo doctorRepo;
    @Autowired
    private PatientRepo patientRepo;
    @Autowired
    private DiagnosisRepo diagnosisRepo;

    public List<Patient> getAllPatients() {
        return patientRepo.findAll();
    }

    public Patient getPatientById(Long id) {
        return patientRepo.findById(id).orElse(new Patient());
    }

    public Doctor getDoctorFromCurrentPrincipal() {
        User user = UserService.getUserFromPrincipal();
        return doctorRepo.findByUser(user).orElse(null);
    }

    public void attachPatient(Patient patient) {
        User user = UserService.getUserFromPrincipal();
        Optional<Doctor> doctor = doctorRepo.findByUser(user);
        if (!doctor.isPresent()){
            LOGGER.warn("User:{} try to attach patient, but he isn't a doctor.", user.getEmail());
            return;
        }

        doctor.get().getPatients().add(patient);
        doctorRepo.save(doctor.get());
        LOGGER.info("Patient: {} attached to doctor: {}.",patient.getUser().getEmail(), user.getEmail());
    }

    public void detachPatient(Patient patient) {
        User user = UserService.getUserFromPrincipal();
        Optional<Doctor> doctor = doctorRepo.findByUser(user);
        if (!doctor.isPresent()){
            LOGGER.warn("User:{} try to attach patient, but he isn't a doctor.", user.getEmail());
            return;
        }

        doctor.get().getPatients().remove(patient);
        doctorRepo.save(doctor.get());
        LOGGER.info("Patient: {} detached from doctor: {}.",patient.getUser().getEmail(), user.getEmail());
    }

    public Doctor getDoctorById(Long doctorId) {
        return doctorRepo.findById(doctorId).orElse(null);
    }

    public Patient getPatientByUser(User user) {
        return patientRepo.findByUser(user).orElse(null);
    }

    public List<Diagnosis> getAllDiagnoses() {
        return diagnosisRepo.findAll();
    }

    public void changeDiagnosis(Patient patient) {
        Optional<Patient> patientFromDb = patientRepo.findById(patient.getId());
        patientFromDb.get().setDiagnosis(patient.getDiagnosis());
        patientRepo.save(patientFromDb.get());
    }
}
