package com.survey.entity.assessment;

import com.survey.entity.QuestionnaireType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "calculation_model", schema = "public")
public class CalculationModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private BigDecimal accuracy;
  private BigDecimal specificity;
  private BigDecimal sensitivity;

  @OneToOne(fetch = FetchType.LAZY)
  private QuestionnaireType questionnaireType;

  @OneToMany(mappedBy = "calculationModel")
  private List<DirectAssessmentQuestionWeight> weights;
}
