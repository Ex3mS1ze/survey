package com.survey.repository;

import com.survey.entity.Role;
import com.survey.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserWithRolesTests {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;

    private User user;
    private Role roleUser;

    @Before
    public void setUp() {
        user = new User();
        user.setId(1L);
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
        user.setRoles(new HashSet<>(Collections.singletonList(roleUser)));
    }

    @Test
    public void existedByEmailAndSaveUserTest() {
        assertFalse(userRepo.existsUserByEmail(user.getEmail()));
        User savedUser = userRepo.save(user);
        assertTrue(userRepo.existsUserByEmail(user.getEmail()));
    }

    @Test
    public void addRoleToUserAndSave() {
        Role rolePatient = roleRepo.findById(2L).orElseThrow(() -> new EntityNotFoundException("id = 2L"));
        user.getRoles().add(rolePatient);
        User savedUser = userRepo.save(user);
        assertEquals(user.getRoles().size(), savedUser.getRoles().size());
    }
}
