package entities;

import utilities.StringDbToListConverter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "questions", schema = "public", catalog = "survey")
public class Question {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "text", nullable = false, length = -1)
    private String text;
    @Convert(converter = StringDbToListConverter.class)
    @Column(name = "options", nullable = true, length = -1)
    private List<String> options;
    @Basic
    @Column(name = "description", nullable = true, length = -1)
    private String description;
    @Basic
    @Column(name = "input_type", nullable = false)
    private String inputType;
    @Basic
    @Column(name = "answer_type", nullable = false)
    private String answerType;
    @OneToOne
    @JoinColumn(name = "depends_on_the_question_id", referencedColumnName = "id")
    private Question dependentQuestion;
    @OneToMany(mappedBy = "question")
    private List<Answer> answersById;

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

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    public Object getAnswerType() {
        return answerType;
    }

    public void setAnswerType(String answerType) {
        this.answerType = answerType;
    }

    public Question getDependentQuestion() {
        return dependentQuestion;
    }

    public void setDependentQuestion(Question questionsByDependsOnTheQuestionId) {
        this.dependentQuestion = questionsByDependsOnTheQuestionId;
    }

    public List<Answer> getAnswersById() {
        return answersById;
    }

    public void setAnswersById(List<Answer> answersById) {
        this.answersById = answersById;
    }
}
