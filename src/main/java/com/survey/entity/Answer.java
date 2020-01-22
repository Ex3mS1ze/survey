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
@Table(name = "answers", schema = "public")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "text", nullable = true)
    private String text;
    @Column(name = "text_list", nullable = true)
    @Convert(converter = StringDbToListConverter.class)
    private List<String> textList;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "question_id", referencedColumnName = "id", nullable = false)
    private Question question;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "questionnaire_id", referencedColumnName = "id", nullable = false)
    private Questionnaire questionnaire;

    public Answer() {
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

    @Override
    public String toString() {
        return "Answer{" + "id=" + id + ", text='" + text + '\'' + '}';
    }
}
