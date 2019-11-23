package com.main.repository;

import com.main.entity.Role;
import com.main.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;

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
        user.setRoles(new HashSet<>(Arrays.asList(roleUser)));
    }

    @Test
    public void saveUser() {
        User savedUser = userRepo.save(user);
        assertEquals(savedUser, user);
    }

    @Test
    public void addRoleToUserAndSave() {
        Role rolePatient = roleRepo.findById(2L).orElseThrow(() -> new EntityNotFoundException("id = 2L"));
        user.getRoles().add(rolePatient);
        User savedUser = userRepo.save(user);
        assertEquals(2, savedUser.getRoles().size());
    }

    @Test
    public void findUser() {
        UserRepo user = this.userRepo;
        user.findByEmail("ad");
        assertNotNull(this.user);
    }

    @Test
    public void checkMandatoryFieldTest() {
        assertTrue(user.checkMandatoryFields());
    }
}
