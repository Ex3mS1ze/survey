package com.survey.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Setter
@Getter
@Entity
@Table(name = "questionnaires", schema = "public")
public class Questionnaire implements Serializable {
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
    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id", nullable = false)
    private QuestionnaireType type;

    {
        this.answers = new ArrayList<>();
    }

    public Questionnaire() {
    }

    public Set<String> getAllCategories() {
        Set<String> categories = new HashSet<>();
        this.getType().getQuestions().forEach(question -> categories.add(question.getCategory()));
        return categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Questionnaire))
            return false;
        Questionnaire that = (Questionnaire) o;
        return Objects.equals(id, that.id) && Objects.equals(date, that.date) &&
               Objects.equals(processed, that.processed) && Objects.equals(diagnosis, that.diagnosis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, processed, diagnosis);
    }


    public boolean isAnyAnswerEmpty() {
        for (Answer answer : getAnswers()) {
            if ((answer.getText() == null || answer.getText().isEmpty()) &&
                (answer.getTextList() == null || answer.getTextList().size() == 0)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return "Questionnaire{" + "id=" + id + ", date=" + date + ", processed=" + processed + '}';
    }
}
