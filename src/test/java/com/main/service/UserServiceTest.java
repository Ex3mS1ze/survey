package com.main.service;

import com.main.repository.UserRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
@SpringBootTest
@RunWith(SpringRunner.class)
@EnableJpaRepositories(basePackages = "com.main.repository")
@EntityScan("com.main.entity")
@AutoConfigureDataJpa
public class UserServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    UserRepo userRepo;
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void loadUserByUsername() {
        UserDetails user = userService.loadUserByUsername("ad");
        assertNotNull(user);
    }
}