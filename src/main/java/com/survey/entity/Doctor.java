package com.survey.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "doctors", schema = "public")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "description", nullable = true)
    private String description;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "doctors_patients",
            joinColumns = {
                    @JoinColumn(name = "doctor_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "patient_id", referencedColumnName = "id")}
    )
    private List<Patient> patients;

    public Doctor() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Doctor))
            return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(id, doctor.id) && Objects.equals(description, doctor.description) && Objects.equals(user, doctor.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, user);
    }

    @Override
    public String toString() {
        return "Doctor{" + "id=" + id + ", user=" + user + '}';
    }
}
