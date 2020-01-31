package com.survey.controller;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class TestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @WithUserDetails(value = "patient@mail.ru", userDetailsServiceBeanName = "myUserDetailsService")
    @Test
    public void getTestPage() throws Exception {
        mockMvc.perform(get("/test").param("typeName", "gastroenterological"))
               .andDo(print())
               .andExpect(authenticated())
               .andExpect(status().isOk())
               .andExpect(xpath("//*[@id='testForm']/div/div/div").nodeCount(greaterThan(20)));
    }

    @Test
    @Ignore
    public void saveChanges() throws Exception {
    }

    @Test
    @Ignore
    public void operateQuestionnaire() throws Exception {
    }

    @WithUserDetails(value = "patient@mail.ru", userDetailsServiceBeanName = "myUserDetailsService")
    @Test
    public void getTestHistory() throws Exception {
        mockMvc.perform(get("/test/history"))
               .andDo(print())
               .andExpect(authenticated())
               .andExpect(status().isOk())
               .andExpect(xpath("//*[@id='patientHistoryTable']/tbody/tr").nodeCount(greaterThan(2)));
    }

    @Test
    public void editQuestionnaire() throws Exception {
    }

    @Test
    public void viewQuestionnaire() throws Exception {
    }
}