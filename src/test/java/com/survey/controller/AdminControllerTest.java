package com.survey.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(username = "notAdmin", roles = {"USER", "PATIENT", "DOCTOR", "ANALYTIC"})
    @Test
    public void accessDeniedTest() throws Exception {
        mockMvc.perform(get("/admin/users"))
               .andDo(print())
               .andExpect(authenticated())
               .andExpect(status().isForbidden());
    }

    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @Test
    public void correctAdminTest() throws Exception {
        mockMvc.perform(get("/admin/users")).andDo(print()).andExpect(authenticated()).
                andExpect(status().isOk());
    }

    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @Test
    public void editUserPageTest() throws Exception {
        mockMvc.perform(get("/admin/edit_user").param("userId", "2"))
               .andDo(print())
               .andExpect(authenticated())
               .andExpect(status().isOk())
               .andExpect(xpath("//*[@id='email']/@value").exists());
    }
}