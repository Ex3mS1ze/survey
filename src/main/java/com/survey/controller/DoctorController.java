package com.survey.controller;

import com.survey.entity.Diagnosis;
import com.survey.entity.Doctor;
import com.survey.entity.Patient;
import com.survey.service.DoctorService;
import com.survey.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DoctorController {
    private static final Logger LOGGER = LogManager.getLogger(DoctorController.class.getName());

    @Autowired
    UserService userService;
    @Autowired
    DoctorService doctorService;

    @GetMapping("/patients")
    public String getPatientsPage(Model model){
        List<Patient> patients = doctorService.getAllPatients();
        model.addAttribute("patients", patients);
        return "patients";
    }

    @GetMapping("/patient/profile")
    public String getPatientProfilePage(@RequestParam("patientId") Long patientId, Model model){
        Doctor doctor = doctorService.getDoctorFromCurrentPrincipal();
        if (doctor == null) {
            LOGGER.warn("Can't find doctor by current principal.");
            return "redirect:/patients";
        }

        Patient patient = doctorService.getPatientById(patientId);
        List<Diagnosis> diagnoses = doctorService.getAllDiagnoses();
        model.addAttribute("diagnoses", diagnoses);
        model.addAttribute("patient", patient);
        model.addAttribute("doctor", doctor);
        return "patient-profile";
    }

    @GetMapping("/doctor/attach_patient")
    public String attachPatient(@RequestParam("patientId") Long patientId, Model model){
        Patient patient = doctorService.getPatientById(patientId);
        doctorService.attachPatient(patient);
        return "redirect:/patient/profile?patientId=" + patientId;
    }

    @GetMapping("/doctor/detach_patient")
    public String detachPatient(@RequestParam("patientId") Long patientId, Model model){
        Patient patient = doctorService.getPatientById(patientId);
        doctorService.detachPatient(patient);
        return "redirect:/patient/profile?patientId=" + patientId;
    }

    @GetMapping("/doctor/profile")
    public String getDoctorProfilePage(@RequestParam("doctorId") Long doctorId, Model model) {
        Doctor doctor = doctorService.getDoctorById(doctorId);
        if (doctor == null) {
            LOGGER.info("Doctor with id={} wasn't found.", doctorId);
            return "redirect:/index";
        }

        model.addAttribute("doctor", doctor);
        return "doctor-profile";
    }

    @PostMapping("change_patient_diagnosis")
    public String changePatientDiagnosis(@ModelAttribute("patient") Patient patient) {
        doctorService.changeDiagnosis(patient);

        return "redirect:/patient/profile?patientId=" + patient.getId();
    }
}
