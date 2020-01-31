package com.survey.controller;

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

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class MainControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getMainPage() throws Exception {
        mockMvc.perform(get("/"))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(content().string(containsString("Главная страница")))
               .andExpect(content().string(containsString("Войти")));
    }

    @Test
    public void correctLoginTest() throws Exception {
        mockMvc.perform((formLogin().user("admin@mail.ru").password("ad")))
               .andDo(print())
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/"));
    }

    @Test
    public void badCredentials() throws Exception {
        mockMvc.perform((formLogin().user("admin@mail.ru").password("wrongpassword")))
               .andDo(print())
               .andExpect(redirectedUrl("/login?error"));
    }

    @Test
    public void aboutPageTest() throws Exception {
        mockMvc.perform(get("/about")).andDo(print()).andExpect(status().isOk());
    }

    @WithUserDetails(value = "patient@mail.ru", userDetailsServiceBeanName = "myUserDetailsService")
    @Test
    public void deniedLoginForAuthenticated() throws Exception {
        mockMvc.perform(formLogin().user("admin@mail.ru").password("ad"))
               .andDo(print())
               .andExpect(authenticated())
               .andExpect(status().is3xxRedirection());
    }
}