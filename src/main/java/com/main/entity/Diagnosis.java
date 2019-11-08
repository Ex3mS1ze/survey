package com.main.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "diagnoses", schema = "public", catalog = "survey")
public class Diagnosis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "text", nullable = false, length = -1)
    private String text;
    @OneToMany(mappedBy = "diagnosis")
    private List<Patient> patients;
    @OneToMany(mappedBy = "diagnosis")
    private List<Questionnaire> questionnaires;

    public Diagnosis() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patientsById) {
        this.patients = patientsById;
    }

    public List<Questionnaire> getQuestionnaires() {
        return questionnaires;
    }

    public void setQuestionnaires(List<Questionnaire> questionnairesById) {
        this.questionnaires = questionnairesById;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Diagnosis))
            return false;
        Diagnosis diagnosis = (Diagnosis) o;
        return Objects.equals(id, diagnosis.id) && Objects.equals(text, diagnosis.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text);
    }
}
