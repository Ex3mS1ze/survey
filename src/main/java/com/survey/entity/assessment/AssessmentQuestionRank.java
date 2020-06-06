package com.survey.entity.assessment;

import com.survey.entity.Question;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "assessment_question_rank", schema = "public")
public class AssessmentQuestionRank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "calculation_model_id", referencedColumnName = "id")
    private CalculationModel calculationModel;

    @OneToOne
    private Question question;

    @Column(name = "rank_from")
    private int rangeFrom;
    @Column(name = "rank_to")
    private int rangeTo;
}
