package com.main.entity;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "questionnaires", schema = "public")
public class Questionnaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "date", nullable = false)
    private LocalDateTime date;
    @Basic
    @Column(name = "processed", nullable = false)
    private Boolean processed;
    @OneToMany(mappedBy = "questionnaire", cascade = CascadeType.ALL)
    private List<Answer> answers;
    @ManyToOne
    @JoinColumn(name = "diagnosis_id", referencedColumnName = "id")
    private Diagnosis diagnosis;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    {
        this.answers = new ArrayList<>();
    }

    public Questionnaire() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Boolean getProcessed() {
        return processed;
    }

    public void setProcessed(Boolean processed) {
        this.processed = processed;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answersById) {
        this.answers = answersById;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosesByDiagnosisId) {
        this.diagnosis = diagnosesByDiagnosisId;
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
        if (!(o instanceof Questionnaire))
            return false;
        Questionnaire that = (Questionnaire) o;
        return Objects.equals(id, that.id) && Objects.equals(date, that.date) && Objects.equals(processed, that.processed) && Objects.equals(diagnosis, that.diagnosis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, processed, diagnosis);
    }

    /*@Override
    public String toString() {
        return "Questionnaire{" + "id=" + id + ", date=" + date + ", processed=" + processed + ", answers=" + answers + ", diagnosis=" + diagnosis + ", user=" + user + '}';
    }*/

    public static boolean isFullFilled(Questionnaire questionnaire) {
        List<Answer> answers = questionnaire.getAnswers();
        for (Answer answer : answers) {
            if ((answer.getText() == null || answer.getText().isEmpty()) && (answer.getTextList() == null || answer.getTextList().size() == 0)) {
                return false;
            }
        }

        return true;
    }

}
