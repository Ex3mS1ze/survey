package com.survey.entity;

import com.survey.utilities.StringDbToListConverter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "questions", schema = "public")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "text", nullable = false)
    private String text;
    @Convert(converter = StringDbToListConverter.class)
    @Column(name = "options", nullable = true)
    private List<String> options;
    @Column(name = "description", nullable = true)
    private String description;
    @Column(name = "input_type", nullable = false)
    private String inputType;
    @Column(name = "answer_type")
    private String answerType;
    @OneToOne
    @JoinColumn(name = "depends_on_the_question_id", referencedColumnName = "id")
    private Question dependentQuestion;
    @OneToMany(mappedBy = "question")
    private List<Answer> answersById;
    @Column(name = "category")
    private String category;
    @Transient
    private Integer order;

    public Question() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Question))
            return false;
        Question question = (Question) o;
        return Objects.equals(id, question.id) && Objects.equals(text, question.text) && Objects.equals(inputType, question.inputType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, inputType);
    }

    @Override
    public String toString() {
        return "Question{" + "id=" + id + ", text='" + text + '\'' + ", options=" + options + ", description='" + description + '\'' + ", inputType='" + inputType + '\'' + ", answerType='" + answerType + '\'' + ", dependentQuestion=" + dependentQuestion + ", category='" + category + '\'' + ", order=" + order + '}';
    }
}
