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
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DoctorService {
    private static final Logger LOGGER = LogManager.getLogger(DoctorService.class.getName());

    private final UserRepo userRepo;
    private final DoctorRepo doctorRepo;
    private final PatientRepo patientRepo;
    private final DiagnosisRepo diagnosisRepo;

    @Autowired
    public DoctorService(UserRepo userRepo, DoctorRepo doctorRepo, PatientRepo patientRepo,
                         DiagnosisRepo diagnosisRepo) {
        this.userRepo = userRepo;
        this.doctorRepo = doctorRepo;
        this.patientRepo = patientRepo;
        this.diagnosisRepo = diagnosisRepo;
    }

    public List<Patient> getAllPatients() {
        return patientRepo.findAll();
    }

    public Patient getPatientById(Long id) {
        return patientRepo.findById(id).orElse(new Patient());
    }

    public Doctor getDoctorFromCurrentPrincipal() {
        User user = UserService.getUserFromPrincipal();
        return doctorRepo.findByUser(user).orElseThrow(NoSuchElementException::new);
    }

    public void attachPatient(Patient patient) {
        if (patient.getDoctor() != null) {
            throw new IllegalArgumentException("Patient already attach to doctor" + patient.getDoctor());
        }

        Doctor doctor = getDoctorFromCurrentPrincipal();
        if (doctor == null) {
            throw new UnsupportedOperationException("User try to attach patient, but he isn't a doctor");
        }

        doctor.getPatients().add(patient);
        doctorRepo.save(doctor);
        LOGGER.info("Patient: {} attached to doctor: {}.", patient.getUser().getEmail(), doctor.getUser().getEmail());
    }

    public void detachPatient(Patient patient) {
        Doctor doctor = getDoctorFromCurrentPrincipal();
        if (doctor == null) {
            throw new UnsupportedOperationException("User: try to attach patient, but he isn't a doctor.");
        }

        doctor.getPatients().remove(patient);
        doctorRepo.save(doctor);
        LOGGER.info("Patient: {} detached from doctor: {}.", patient.getUser().getEmail(), doctor.getUser().getEmail());
    }

    public Doctor getDoctorById(Long doctorId) {
        return doctorRepo.findById(doctorId)
                         .orElseThrow(() -> new NoSuchElementException("No doctor with doctorId=" + doctorId));
    }

    public Patient getPatientByUser(User user) {
        return patientRepo.findByUser(user).orElseThrow(NoSuchElementException::new);

    }

    public List<Diagnosis> getAllDiagnoses() {
        return diagnosisRepo.findAll();
    }

    public void changeDiagnosis(Patient patient) {
        Patient patientFromDb = patientRepo.findById(patient.getId()).orElseThrow(NoSuchElementException::new);
        patientFromDb.setDiagnosis(patient.getDiagnosis());
        patientRepo.save(patientFromDb);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepo.findAll();
    }
}
