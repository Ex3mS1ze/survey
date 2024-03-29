package com.survey.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.survey.entity.assessment.AssessmentResult;
import lombok.Getter;
import lombok.Setter;

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
    @JsonView(Views.Id.class)
    private Long id;
    @Basic
    @Column(name = "date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonView(Views.WithoutAnswersQuestionsTypeUser.class)
    private LocalDateTime date;
    @Basic
    @Column(name = "processed", nullable = false)
    @JsonView(Views.WithoutAnswersQuestionsTypeUser.class)
    private Boolean processed;
    @OneToMany(mappedBy = "questionnaire", cascade = CascadeType.ALL)
    //    @JsonManagedReference
    //    @JsonView(Views.WithoutAnswersQuestionsTypeUser.class)
    private List<Answer> answers;
    @ManyToOne
    @JoinColumn(name = "diagnosis_id", referencedColumnName = "id")
    @JsonView(Views.WithoutAnswersQuestionsTypeUser.class)
    private Diagnosis diagnosis;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id", nullable = false)
    @JsonView(Views.WithoutAnswersQuestionsTypeUser.class)
    private QuestionnaireType type;
    @OneToMany(mappedBy = "questionnaire")
    private List<AssessmentResult> assessmentResults;
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
