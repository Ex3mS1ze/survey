package com.survey.controller;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class RegistrationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void registrationPageTest() throws Exception {
        mockMvc.perform(get("/registration"))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(content().string(containsString("Зарегистрироваться")));
    }

    @Test
    @Ignore
    public void handleRegistrationForm() {
    }

    @Test
    public void deniedActivateUser() throws Exception {
        mockMvc.perform(get("/activate/ "))
               .andDo(print())
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/login"));
    }

    @Test
    @Ignore
    public void activateUser() throws Exception {
    }

    @Test
    public void getRestorePasswordPage() throws Exception {
        mockMvc.perform(get("/restore_password")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void emailUsedTest() throws Exception {
        mockMvc.perform(get("/search_used_mail").param("enteredEmail", "admin@mail.ru"))
               .andDo(print())
               .andExpect(status().isOk());
    }
}