package com.survey.entity;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;


public class UserTest {
    private User user;
    private Role roleUser;
    private Role roleAdmin;
    private Role rolePatient;
    private Role roleDoctor;


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

        roleUser = new Role();
        roleUser.setId(1L);
        roleUser.setRolename("ROLE_USER");

        roleAdmin = new Role();
        roleAdmin.setId(3L);
        roleAdmin.setRolename("ROLE_ADMIN");

        rolePatient = new Role();
        rolePatient.setId(2L);
        rolePatient.setRolename("ROLE_PATIENT");

        roleDoctor = new Role();
        roleDoctor.setId(5L);
        roleDoctor.setRolename("ROLE_DOCTOR");

        user.setRoles(new HashSet<>(Arrays.asList(roleUser)));
    }

    @Test
    public void checkMandatoryFieldTest() {
        User user1 = new User();
        user1.setId(3L);
        user1.setActivated(true);
        user1.setRegistrationDate(LocalDateTime.now());

        assertFalse(user1.checkMandatoryFields());
        user1.setEmail("");
        assertFalse(user1.checkMandatoryFields());
        user1.setPassword("");
        assertFalse(user1.checkMandatoryFields());
        user1.setFirstName("");
        assertFalse(user1.checkMandatoryFields());
        user1.setSecondName("");
        assertFalse(user1.checkMandatoryFields());
        user1.setGender("");
        assertFalse(user1.checkMandatoryFields());
        user1.setPassword("");
        assertFalse(user1.checkMandatoryFields());
        user1.setPhoneNumber("");
        assertFalse(user1.checkMandatoryFields());
        user1.setRoles(new HashSet<>());
        assertFalse(user1.checkMandatoryFields());

        user1.setEmail("fsda@m");
        assertFalse(user1.checkMandatoryFields());
        user1.setPassword("dsadAsad21");
        assertFalse(user1.checkMandatoryFields());
        user1.setFirstName("ADsadss");
        assertFalse(user1.checkMandatoryFields());
        user1.setSecondName("Dsdasd");
        assertFalse(user1.checkMandatoryFields());
        user1.setGender("male");
        assertFalse(user1.checkMandatoryFields());
        user1.setPhoneNumber("89994501020");
        assertFalse(user1.checkMandatoryFields());
        user1.getRoles().add(roleUser);
        assertTrue(user1.checkMandatoryFields());
    }

    @Test
    public void isRoleTest() {

        assertFalse(user.isAdmin());
        user.getRoles().add(roleAdmin);
        assertTrue(user.isAdmin());

        assertFalse(user.isDoctor());
        user.getRoles().add(roleDoctor);
        assertTrue(user.isDoctor());

        assertFalse(user.isPatient());
        user.getRoles().add(rolePatient);
        assertTrue(user.isPatient());
    }

    @Test
    public void equalsTest() {
        User user1 = new User();
        assertNotEquals(user, user1);
        user1.setId(user.getId());
        assertNotEquals(user, user1);
        user1.setActivated(user.getActivated());
        assertNotEquals(user, user1);
        user1.setEmail(user.getEmail());
        assertNotEquals(user, user1);
        user1.setFirstName(user.getFirstName());
        assertNotEquals(user, user1);
        user1.setSecondName(user.getSecondName());
        assertNotEquals(user, user1);
        user1.setGender(user.getGender());
        assertNotEquals(user, user1);
        user1.setPassword(user.getPassword());
        assertNotEquals(user, user1);
        user1.setPhoneNumber(user.getPhoneNumber());
        assertNotEquals(user, user1);
        user1.setRegistrationDate(user.getRegistrationDate());
        assertNotEquals(user, user1);
        user1.setRoles(user.getRoles());

        assertEquals(user, user1);
        assertEquals(user.hashCode(), user1.hashCode());
    }


}
