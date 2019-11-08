package entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "doctors", schema = "public", catalog = "survey")
public class Doctor {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "description", nullable = true, length = -1)
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
}
