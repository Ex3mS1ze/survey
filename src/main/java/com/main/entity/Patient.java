package com.main.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "patients", schema = "public")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "diagnosis_id", referencedColumnName = "id")
    private Diagnosis diagnosis;
    @ManyToOne
    @JoinTable(
            name = "doctors_patients",
            joinColumns = {
                    @JoinColumn(name = "patient_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "doctor_id", referencedColumnName = "id")}
    )
    private Doctor doctor;

    public Patient() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User usersByUserId) {
        this.user = usersByUserId;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Patient))
            return false;
        Patient patient = (Patient) o;
        return Objects.equals(id, patient.id) && Objects.equals(user, patient.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user);
    }

    public void setDiagnosis(Diagnosis diagnosesByDiagnosisId) {
        this.diagnosis = diagnosesByDiagnosisId;
    }
}
