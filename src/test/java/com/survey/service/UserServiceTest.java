package com.survey.service;

import com.survey.entity.Role;
import com.survey.entity.User;
import com.survey.repository.ActivationCodeRepo;
import com.survey.repository.UserRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
//@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, FlywayTestExecutionListener.class, SqlScriptsTestExecutionListener.class})
//@FlywayTest
//@FlywayTest(locationsForMigrate = {"/db/migration"})
public class UserServiceTest {
    private final String mailFromScript = "TESTING@mail.ru";
    @Autowired
    UserService userService;
    @Autowired
    UserRepo userRepo;
    @Autowired
    ActivationCodeRepo activationCodeRepo;
    @MockBean
    private EmailSender emailSender;

    private User userForTest;
    private Role roleUser;
    private Role roleAdmin;
    private Role rolePatient;
    private Role roleDoctor;


    @Before
    public void setUp() {
        userForTest = new User();
        // ID=2 exist
        userForTest.setId(2L);
        userForTest.setActivated(true);
        userForTest.setEmail("admin@mail.ru");
        userForTest.setFirstName("name");
        userForTest.setSecondName("name");
        userForTest.setGender("male");
        userForTest.setPassword("pass");
        userForTest.setPhoneNumber("89994501020");
        userForTest.setRegistrationDate(LocalDateTime.now());

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

        userForTest.setRoles(new HashSet<>(Arrays.asList(roleUser)));

    }

    @Test
    public void LoadUserByUsername() {
        UserDetails user = userService.loadUserByUsername("admin@mail.ru");
        assertNotNull(user);
    }

    @Test
    public void saveNewUser() {
        userService.saveNewUser(userForTest, roleUser.getRolename());
        Optional<User> user = userRepo.findByEmail(this.userForTest.getEmail());
        assertTrue(user.isPresent());
    }

    @Test
    @WithUserDetails(value = "admin@mail.ru", userDetailsServiceBeanName = "myUserDetailsService")
    public void editUserData() {
        assertTrue(userRepo.existsById(userForTest.getId()));
        User user = userRepo.findById(userForTest.getId()).get();
        user.setFirstName("NewName123");
        userService.editUserData(user);

        User userEdited = userRepo.findById(userForTest.getId()).get();
        assertEquals(user, userEdited);
    }

    @Test
    public void getAllUsers() {
        assertTrue(userService.getAllUsers().size() > 0);
    }

    @Test
    public void getUserById() {
        assertNotNull(userService.getUserById(2L));
    }

    @Test
    public void createAndSendActivationCode() {
        // ID=1 doesn't exist
        userForTest.setEmail("emailDoesntUsedByAnyone@supermail.ru");
        assertTrue(userService.saveNewUser(userForTest, roleUser.getRolename()));
        User savedUser = userRepo.findByEmail(userForTest.getEmail()).get();
        assertTrue(userService.createAndSendActivationCode(savedUser));
        assertTrue(activationCodeRepo.findByUser(savedUser).isPresent());
        verify(emailSender).send(eq(savedUser.getEmail()), contains("Активация аккаунта"), anyString());
    }

    @Sql(scripts = "/db/scripts/add_data_test.sql")
    @Sql(scripts = "/db/scripts/rollback_data_test.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void activateUser() {
        Optional<User> user = userRepo.findByEmail(mailFromScript);
        assertTrue(user.isPresent() && !user.get().getActivated());
        userService.activateUser("111222333444555");
        user = userRepo.findByEmail(mailFromScript);
        assertTrue(user.get().getActivated());
    }

    @Sql(scripts = "/db/scripts/add_data_test.sql")
    @Sql(scripts = "/db/scripts/rollback_data_test.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void restorePassword() {
        Optional<User> user = userRepo.findByEmail(mailFromScript);
        String oldPass = user.get().getPassword();
        assertTrue(userService.restorePassword(mailFromScript));
        user = userRepo.findByEmail(mailFromScript);
        assertNotEquals(user.get().getPassword(), oldPass);
        verify(emailSender).send(eq(mailFromScript), contains("Восстановление пароля"), anyString());
    }

    @Sql(scripts = "/db/scripts/add_data_test.sql")
    @Sql(scripts = "/db/scripts/rollback_data_test.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @WithUserDetails(value = "TESTING@mail.ru", userDetailsServiceBeanName = "myUserDetailsService")
    @Test
    public void changePassword() {
        Optional<User> user = userRepo.findByEmail(mailFromScript);
        String oldPass = user.get().getPassword();
        assertTrue(userService.changePassword("ad", "newPass"));
        assertNotEquals(user.get().getPassword(), oldPass);
    }

    @Test
    public void getAllRoles() {
        assertTrue(userService.getAllRoles().size() > 0);
    }

    @Sql(scripts = "/db/scripts/add_data_test.sql")
    @Sql(scripts = "/db/scripts/rollback_data_test.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void isEmailUsed() {
        assertTrue(userService.isEmailUsed(mailFromScript));
    }

    @Sql(scripts = "/db/scripts/add_data_test.sql")
    @Sql(scripts = "/db/scripts/rollback_data_test.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void findByEmail() {
        assertNotNull(userService.findByEmail(mailFromScript));
    }
}