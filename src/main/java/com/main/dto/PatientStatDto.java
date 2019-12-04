package com.main.dto;

import com.main.entity.Patient;

public class PatientStatDto {
    private Patient patient;
    private Long processedQuestionnaire;
    private Long totalQuestionnaires;

    public PatientStatDto() {
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Long getProcessedQuestionnaire() {
        return processedQuestionnaire;
    }

    public void setProcessedQuestionnaire(Long processedQuestionnaire) {
        this.processedQuestionnaire = processedQuestionnaire;
    }

    public Long getTotalQuestionnaires() {
        return totalQuestionnaires;
    }

    public void setTotalQuestionnaires(Long totalQuestionnaires) {
        this.totalQuestionnaires = totalQuestionnaires;
    }
}
