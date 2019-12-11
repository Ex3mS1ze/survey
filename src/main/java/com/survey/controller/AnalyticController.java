package com.survey.controller;

import com.survey.dto.DoctorStatDto;
import com.survey.dto.PatientStatDto;
import com.survey.service.AnalyticService;
import com.survey.service.DoctorService;
import com.survey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AnalyticController {
    @Autowired
    UserService userService;
    @Autowired
    DoctorService doctorService;
    @Autowired
    AnalyticService analyticService;

    @GetMapping("/statistic")
    public String getStatisticPage(Model model) {
        List<DoctorStatDto> doctors = analyticService.getDoctorsStatistic();
        List<PatientStatDto> patients = analyticService.getPatientsStatistic();

        model.addAttribute("doctors", doctors);
        model.addAttribute("patients", patients);

        return "statistic";
    }
}
