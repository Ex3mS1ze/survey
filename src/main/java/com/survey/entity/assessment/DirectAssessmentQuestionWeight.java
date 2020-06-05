package com.survey.entity.assessment;

import com.survey.entity.Question;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "direct_assessment_question_weight", schema = "public")
public class DirectAssessmentQuestionWeight {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "calculation_model_id", referencedColumnName = "id")
  private CalculationModel calculationModel;

  @OneToOne
  private Question question;

  private BigDecimal weight;
}
