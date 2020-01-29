package com.survey.service;

import com.survey.dto.DoctorStatDto;
import com.survey.dto.PatientStatDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class AnalyticServiceTest {
    @Autowired
    AnalyticService analyticService;

    @Test
    public void getDoctorsStatistic() {
        List<DoctorStatDto> doctorsStatistic = analyticService.getDoctorsStatistic();
        assertTrue(doctorsStatistic.size() > 0);
    }

    @Test
    public void getPatientsStatistic() {
        List<PatientStatDto> patientsStatistic = analyticService.getPatientsStatistic();
        assertTrue(patientsStatistic.size() > 0);
    }
}