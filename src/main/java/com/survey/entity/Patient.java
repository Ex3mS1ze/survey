package com.survey.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Setter
@Getter
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

    @Override
    public String toString() {
        return "Patient{" + "id=" + id + ", user=" + user + '}';
    }
}
