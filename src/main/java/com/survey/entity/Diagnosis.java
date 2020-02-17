package com.survey.entity;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "diagnoses", schema = "public")
public class Diagnosis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "text", nullable = false)
    @JsonView(Views.WithoutAnswersQuestionsTypeUser.class)
    private String text;
    @OneToMany(mappedBy = "diagnosis")
    private List<Patient> patients;
    @OneToMany(mappedBy = "diagnosis")
    private List<Questionnaire> questionnaires;
    @Column(name = "score_for_low_chance")
    private BigDecimal scoreForLow;
    @Column(name = "score_for_mid_chance")
    private BigDecimal scoreForMid;
    @Column(name = "score_for_high_chance")
    private BigDecimal scoreForHigh;

    public Diagnosis() {
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

    @Override
    public String toString() {
        return "Diagnosis{" + "id=" + id + ", text='" + text + '\'' + '}';
    }
}
