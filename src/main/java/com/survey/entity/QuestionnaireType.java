package com.survey.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "questionnaire_types")
public class QuestionnaireType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "type")
    private String name;
    @Column(name = "description")
    private String description;
    @Transient
    private List<Question> questions;

    public QuestionnaireType() {
    }

    @Override
    public String toString() {
        return "QuestionnaireType{" + "id=" + id + ", name='" + name + '\'' + ", description='" + description + '\'' + '}';
    }
}
