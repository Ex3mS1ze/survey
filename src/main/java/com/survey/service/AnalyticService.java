package com.survey.service;

import com.survey.dto.DoctorStatDto;
import com.survey.dto.PatientStatDto;
import com.survey.entity.Doctor;
import com.survey.entity.Patient;
import com.survey.repository.DoctorRepo;
import com.survey.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnalyticService {
    private final DoctorRepo doctorRepo;
    private final PatientRepo patientRepo;
    private final DoctorService doctorService;

    @Autowired
    public AnalyticService(DoctorRepo doctorRepo, PatientRepo patientRepo, DoctorService doctorService) {
        this.doctorRepo = doctorRepo;
        this.patientRepo = patientRepo;
        this.doctorService = doctorService;
    }

    public List<DoctorStatDto> getDoctorsStatistic() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        List<DoctorStatDto> doctorStatDtos = new ArrayList<>();

        for (Doctor doctor : doctors) {
            DoctorStatDto doctorStatDto = new DoctorStatDto();
            doctorStatDto.setDoctor(doctor);
            doctorStatDto.setPatientCount((long) doctor.getPatients().size());
            doctorStatDto.setDiagnoseEstablished(doctor.getPatients()
                    .stream().filter(p -> p.getDiagnosis() != null).count());
            doctorStatDtos.add(doctorStatDto);
        }

        return doctorStatDtos;
    }

    public List<PatientStatDto> getPatientsStatistic() {
        List<Patient> patients = doctorService.getAllPatients();
        List<PatientStatDto> patientStatDtos = new ArrayList<>();

        for (Patient patient : patients) {
            PatientStatDto patientStatDto = new PatientStatDto();
            patientStatDto.setPatient(patient);
            patientStatDto.setProcessedQuestionnaire(patient.getUser().getQuestionnaires()
                    .stream().filter(q -> q.getProcessed()).count());
            patientStatDto.setTotalQuestionnaires((long) patient.getUser().getQuestionnaires().size());
            patientStatDtos.add(patientStatDto);
        }

        return patientStatDtos;
    }
}
