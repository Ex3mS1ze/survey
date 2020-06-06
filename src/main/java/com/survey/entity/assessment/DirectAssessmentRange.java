package com.survey.entity.assessment;

import com.survey.entity.Diagnosis;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "assessment_range", schema = "public")
public class DirectAssessmentRange {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  private CalculationModel calculationModel;

  private BigDecimal min;
  private BigDecimal max;

  @ManyToOne
  @JoinColumn(name = "diagnosis_min", referencedColumnName = "id")
  private Diagnosis diagnosisMin;
  @ManyToOne
  @JoinColumn(name = "diagnosis_max", referencedColumnName = "id")
  private Diagnosis diagnosisMax;
}
