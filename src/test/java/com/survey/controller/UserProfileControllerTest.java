package com.survey.controller;

import com.survey.entity.User;
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

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserProfileControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @WithUserDetails(value = "patient@mail.ru", userDetailsServiceBeanName = "myUserDetailsService")
    @Test
    public void getProfilePage() throws Exception {
        mockMvc.perform(get("http://localhost/profile"))
               .andDo(print())
               .andExpect(authenticated())
               .andExpect(xpath("//*[@id='inputEmail']/@value").string("patient@mail.ru"));
    }

    @Ignore //TODO deal with bindingResult
    @WithUserDetails(value = "admin@mail.ru", userDetailsServiceBeanName = "myUserDetailsService")
    @Test
    public void changePasswordTest() throws Exception {
        User user = new User();
        user.setPassword("add");
        user.setPasswordConfirm("add");
        user.setOldPassword("ad"); //real password from db

        mockMvc.perform(post("/changePassword").with(csrf()).flashAttr("userForm", user))
               .andDo(print())
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/login"))
               .andExpect(unauthenticated());

    }

    @Ignore //TODO deal with bindingResult
    @WithUserDetails(value = "admin@mail.ru", userDetailsServiceBeanName = "myUserDetailsService")
    @Test
    public void deniedChangePasswordTest() throws Exception {
        User user = new User();
        user.setPassword("");
        user.setOldPassword(""); //real password from db

        mockMvc.perform(post("/changePassword").with(csrf()).flashAttr("userForm", user))
               .andExpect(authenticated())
               .andDo(print())
               .andExpect(status().is3xxRedirection());

    }

    @WithUserDetails(value = "admin@mail.ru", userDetailsServiceBeanName = "myUserDetailsService")
    @Test
    public void editPersonalData() throws Exception {
        User user = new User();
        user.setFirstName("firstName");
        user.setSecondName("secondName");

        mockMvc.perform(post("/editPersonalData").with(csrf()).flashAttr("userForm", user))
               .andExpect(authenticated())
               .andDo(print())
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/profile"));
    }

    @WithUserDetails(value = "admin@mail.ru", userDetailsServiceBeanName = "myUserDetailsService")
    @Test
    public void deniedEditPersonalData() throws Exception {
        mockMvc.perform(post("/editPersonalData").with(csrf()).sessionAttr("userForm", new User()))
               .andExpect(authenticated())
               .andDo(print())
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/profile"));
    }


}