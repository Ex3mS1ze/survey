package com.survey.entity;

import com.survey.utilities.StringDbToListConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "question_weights", schema = "public")
public class QuestionWeight {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private Question question;
    @ManyToOne
    @JoinColumn(name = "questionnaire_type_id", referencedColumnName = "id")
    private QuestionnaireType questionnaireType;
    @Column(name = "lower_bound")
    private BigDecimal lowBound;
    @Column(name = "upper_bound")
    private BigDecimal upBound;
    @Column(name = "weight_for_up")
    private BigDecimal gtWeight;
    @Column(name = "weight_for_low")
    private BigDecimal ltWeight;
    @Column(name = "array_of_weights")
    @Convert(converter = StringDbToListConverter.class)
    private List<String> weightsList;
    @Column(name = "bound_type")
    private String boundType;
    @ManyToOne
    @JoinColumn(name = "diagnosis_id", referencedColumnName = "id")
    private Diagnosis diagnosis;

    public QuestionWeight() {
    }
}

