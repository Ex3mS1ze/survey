package com.survey.service;

import com.survey.entity.Diagnosis;
import com.survey.entity.Doctor;
import com.survey.entity.Patient;
import com.survey.entity.User;
import com.survey.repository.DiagnosisRepo;
import com.survey.repository.DoctorRepo;
import com.survey.repository.PatientRepo;
import com.survey.repository.UserRepo;
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
        Doctor doctor = getDoctorFromCurrentPrincipal();
        if (doctor == null){
            LOGGER.warn("User try to attach patient, but he isn't a doctor.");
            return;
        }

        doctor.getPatients().add(patient);
        doctorRepo.save(doctor);
        LOGGER.info("Patient: {} attached to doctor: {}.",patient.getUser().getEmail(), doctor.getUser().getEmail());
    }

    public void detachPatient(Patient patient) {
        Doctor doctor = getDoctorFromCurrentPrincipal();
        if (doctor == null){
            LOGGER.warn("User: try to attach patient, but he isn't a doctor.");
            return;
        }

        doctor.getPatients().remove(patient);
        doctorRepo.save(doctor);
        LOGGER.info("Patient: {} detached from doctor: {}.",patient.getUser().getEmail(), doctor.getUser().getEmail());
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

    public List<Doctor> getAllDoctors() {
        return doctorRepo.findAll();
    }
}