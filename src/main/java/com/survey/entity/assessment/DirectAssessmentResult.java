package com.survey.entity.assessment;

import com.survey.entity.Diagnosis;
import com.survey.entity.Questionnaire;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@ToString(of = {"calculationModel", "sum", "diagnosis"})
@Table(name = "assessment_result", schema = "public")
public class DirectAssessmentResult {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "questionnaire_id", referencedColumnName = "id")
  private Questionnaire questionnaire;

  @OneToOne
  @JoinColumn(name = "calculation_model_id", referencedColumnName = "id")
  private CalculationModel calculationModel;

  private BigDecimal sum;

  @ManyToOne
  @JoinColumn(name = "diagnosis_id", referencedColumnName = "id")
  private Diagnosis diagnosis;

  private boolean confirmed;
}
