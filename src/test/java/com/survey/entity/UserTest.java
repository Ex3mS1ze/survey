package com.survey.entity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserTest {
    private User user;
    private Role roleAdmin;

    @Before
    public void setUp() {
        user = new User();
        user.setId(3L);
        user.setActivated(true);
        user.setEmail("dru@gmail.com");
        user.setFirstName("name");
        user.setSecondName("name");
        user.setGender("male");
        user.setPassword("pass");
        user.setPhoneNumber("89994501020");
        user.setRegistrationDate(LocalDateTime.now());
        roleAdmin = new Role();
        roleAdmin.setId(1L);
        roleAdmin.setRolename("ROLE_USER");
        user.setRoles(new HashSet<>(Arrays.asList(roleAdmin)));
    }

    /*@Test
    @BeforeEach
    public void checkMandatoryFieldTest() {
        assertTrue(user.checkMandatoryFields());
        user.setEmail("");
        assertFalse(user.checkMandatoryFields());
        user.setPassword("");
        assertFalse(user.checkMandatoryFields());
        user.setFirstName("");
        assertFalse(user.checkMandatoryFields());
        user.setRoles(new HashSet<>());
        assertFalse(user.checkMandatoryFields());

    }*/

    @Test
    public void isAdminTest() {
        roleAdmin = new Role();
        roleAdmin.setId(3L);
        roleAdmin.setRolename("ROLE_ADMIN");

        user.getRoles().add(roleAdmin);
        assertTrue(user.isAdmin());
    }
}
