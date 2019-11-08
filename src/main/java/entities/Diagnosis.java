package entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "diagnoses", schema = "public", catalog = "survey")
public class Diagnosis {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "text", nullable = false, length = -1)
    private String text;
    @OneToMany(mappedBy = "diagnosis")
    private List<Patient> patients;
    @OneToMany(mappedBy = "diagnosis")
    private List<Questionnaire> questionnaires;

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
}
