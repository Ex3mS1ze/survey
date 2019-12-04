package com.main.service;

import com.main.dto.DoctorStatDto;
import com.main.dto.PatientStatDto;
import com.main.entity.Doctor;
import com.main.entity.Patient;
import com.main.repository.DoctorRepo;
import com.main.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnalyticService {
    @Autowired
    DoctorRepo doctorRepo;
    @Autowired
    PatientRepo patientRepo;
    @Autowired
    DoctorService doctorService;

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
