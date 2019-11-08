package com.main.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "answers", schema = "public", catalog = "survey")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "text", nullable = true, length = -1)
    private String text;
    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id", nullable = false)
    private Question question;
    @ManyToOne
    @JoinColumn(name = "questionnaire_id", referencedColumnName = "id", nullable = false)
    private Questionnaire questionnaire;

    public Answer() {
    }

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

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question questionsByQuestionId) {
        this.question = questionsByQuestionId;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnairesByQuestionnaireId) {
        this.questionnaire = questionnairesByQuestionnaireId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Answer))
            return false;
        Answer answer = (Answer) o;
        return Objects.equals(id, answer.id) && Objects.equals(text, answer.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text);
    }
}
