package com.survey.dto;

import com.survey.entity.Doctor;


public class DoctorStatDto {
    private Doctor doctor;
    private Long patientCount;
    private Long diagnoseEstablished;

    public DoctorStatDto() {
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Long getPatientCount() {
        return patientCount;
    }

    public void setPatientCount(Long patientCount) {
        this.patientCount = patientCount;
    }

    public Long getDiagnoseEstablished() {
        return diagnoseEstablished;
    }

    public void setDiagnoseEstablished(Long diagnoseEstablished) {
        this.diagnoseEstablished = diagnoseEstablished;
    }
}
