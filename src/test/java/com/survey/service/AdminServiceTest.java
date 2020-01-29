package com.survey.service;

import com.survey.entity.User;
import com.survey.repository.UserRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class AdminServiceTest {
    @Autowired
    UserRepo userRepo;
    @Autowired
    AdminService adminService;

    @WithUserDetails(value = "admin@mail.ru", userDetailsServiceBeanName = "myUserDetailsService")
    @Test
    public void saveUserChanges() {
        User user = userRepo.findAll(PageRequest.of(0, 1)).get().collect(Collectors.toList()).get(0);
        user.setFirstName("Joshua");
        user.setSecondName("Bog");
        user.setPhoneNumber("999999999");
        user.setActivated(!user.getActivated());
        User user1 = adminService.saveUserChanges(user);
        assertEquals(user, user1);
    }
}