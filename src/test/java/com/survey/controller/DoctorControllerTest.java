package com.survey.controller;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class DoctorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @WithUserDetails(value = "admin@mail.ru", userDetailsServiceBeanName = "myUserDetailsService")
    @Test
    public void patientsPageTest() throws Exception {
        mockMvc.perform(get("/patients"))
               .andDo(print())
               .andExpect(authenticated())
               .andExpect(status().isOk())
               .andExpect(xpath("//*[@id='patientsTable']/tbody/tr").nodeCount(greaterThan(2)));
    }

    @WithUserDetails(value = "admin@mail.ru", userDetailsServiceBeanName = "myUserDetailsService")
    @Test
    public void patientProfilePage() throws Exception {
        mockMvc.perform(get("/patient/profile").param("patientId", "22"))
               .andDo(print())
               .andExpect(authenticated())
               .andExpect(status().isOk())
               .andExpect(xpath("//*[@id='patientHistoryTable']/tbody/tr").nodeCount(greaterThan(2)));
    }

    @Test
    @Ignore
    public void attachPatient() throws Exception {
    }

    @Test
    @Ignore
    public void detachPatient() throws Exception {
    }

    @WithUserDetails(value = "patient@mail.ru", userDetailsServiceBeanName = "myUserDetailsService")
    @Test
    public void doctorProfilePage() throws Exception {
        mockMvc.perform(get("/doctor/profile").param("doctorId", "1"))
               .andDo(print())
               .andExpect(authenticated())
               .andExpect(status().isOk());
    }

    @Test
    public void changePatientDiagnosis() throws Exception {
    }
}