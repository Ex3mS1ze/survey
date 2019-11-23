package com.main.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
    @OneToMany
    @JoinTable(
            name = "doctors_patients",
            joinColumns = {
                    @JoinColumn(name = "patient_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "doctor_id", referencedColumnName = "id")}
    )
    private List<Patient> patients;

    public Doctor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User usersByUserId) {
        this.user = usersByUserId;
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
}
