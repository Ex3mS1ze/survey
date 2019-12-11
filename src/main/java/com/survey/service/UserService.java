package com.survey.service;

import com.survey.entity.*;
import com.survey.repository.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserService implements UserDetailsService, ApplicationListener<AuthenticationSuccessEvent> {
    private static final Logger LOGGER = LogManager.getLogger(UserService.class.getName());

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private PatientRepo patientRepo;
    @Autowired
    private DoctorRepo doctorRepo;
    @Autowired
    private ActivationCodeRepo activationCodeRepo;
    @Autowired
    private EmailSender emailSender;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User userFromRepo = userRepo.findByEmail(email);

        if (userFromRepo == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return userFromRepo;
    }

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent authenticationSuccessEvent) {
        User user = (User) authenticationSuccessEvent.getAuthentication().getPrincipal();
        User userFromDb = userRepo.findByEmail(user.getEmail());
        userFromDb.setLastVisitDate(LocalDateTime.now());
        userRepo.save(userFromDb);

        LOGGER.info("User {} logged in.", user.getEmail());
    }

    /**
     * Save new User.
     *
     * <p>Before save check if <tt>User</tt> with same mail not exist and all mandatory fields fulfilled.</p>
     *
     * @param user
     * @return <tt>true</tt> - if new <tt>User</tt> saved. <tt>false</tt> - if wasn't.
     */
    public boolean saveNewUser(User user, String userRole) {
        user.setRoles(new HashSet<Role>() {{
            add(roleRepo.findByRolename("ROLE_USER"));
        }});

        user.setRegistrationDate(LocalDateTime.now());
        user.setActivated(false);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if (user.checkMandatoryFields() && !userRepo.existsUserByEmail(user.getEmail())) {
            if (userRole.equals("patient")) {
                user.getRoles().add(roleRepo.findByRolename("ROLE_PATIENT"));
                user = userRepo.save(user);
                Patient patient = new Patient();
                patient.setUser(user);
                patientRepo.save(patient);
                LOGGER.info("New patient: {} added.", user.getEmail());
            } else if(userRole.equals("doctor")){
                user = userRepo.save(user);
                Doctor doctor = new Doctor();
                doctor.setUser(user);
                doctorRepo.save(doctor);
                LOGGER.info("New doctor: {} added.", user.getEmail());
            }
            else {
                userRepo.save(user);
                LOGGER.info("New user: {} added.", user.getEmail());
            }

            return true;
        }

        LOGGER.warn("SaveNewUser rejected. User with same email {} exist or not all fields fulfilled", user.getEmail());
        return false;
    }

    /**
     * Edit <tt>User</tt> data (firstName, secondName, phoneNumber) and save changes in DB.
     *
     * @param user
     */
    public void editUserData(User user) {
        User userFromSecurityContext = getUserFromPrincipal();
        user.setId(userFromSecurityContext.getId());
        Optional<User> userFromDb = userRepo.findById(user.getId());
        if (userFromDb.isPresent()) {
            userFromDb.get().setFirstName(user.getFirstName());
            userFromDb.get().setSecondName(user.getSecondName());
            userFromDb.get().setPhoneNumber(user.getPhoneNumber());
            userRepo.save(userFromDb.get());
            return;
        }

        LOGGER.warn("EditUser rejected. User with id {} doesn't exist or not all fields fulfilled", user.getId());
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(Long id) {
        return userRepo.findById(id).orElse(new User());
    }

    /**
     * <p>Check if activationCode for user already exist. If exist execution is interrupted.</p>
     * <p>If not exits - create new <tt>ActivationCode</tt> for existed <tt>User</tt>.</p>
     * <p>Save <b>activationCode</b> in DB.</p>
     * <p>Forced sets <b>activated</b> variable for User to false. Save changes in DB.</p>
     * <p>Send message with <b>activationCode</b> to User <b>mail</b>.</p>
     *
     * @param user
     * @return <tt>true</tt> - if new code created and sended. <tt>false</tt> - if not.
     */
    public boolean createAndSendActivationCode(User user) {
        Optional<ActivationCode> activationCodeFromDB = activationCodeRepo.findByUser(user);
        if (activationCodeFromDB.isPresent()) {
            return false;
        }
        ActivationCode activationCode = new ActivationCode(user);
        activationCodeRepo.save(activationCode);

        user.setActivated(false);
        userRepo.save(user);

        String message = String.format("Здравствуйте, %s! \n + Для активации аккаунта перейдите по ссылке: " + "http://localhost:8080/activate/%s", user.getFirstName(), activationCode.getCode());
        emailSender.send(user.getEmail(), "Активация аккаунта", message);

        LOGGER.info("Created activation code for User:{}, current activation status: {}", user.getEmail(), user.getActivated());
        return true;
    }

    public boolean activateUser(String code) {
        Optional<ActivationCode> activationCodeFromDb = activationCodeRepo.findByCode(code);
        if (!activationCodeFromDb.isPresent()) {
            LOGGER.warn("Activation code:{} for wasn't found", code);
            return false;
        }

        User user = activationCodeFromDb.get().getUser();
        user.setActivated(true);
        userRepo.save(user);
        activationCodeRepo.delete(activationCodeFromDb.get());
        LOGGER.info("User:{} activated. Record: id={} from activation_code table deleted.", user.getEmail(), activationCodeFromDb.get().getId());
        return true;
    }

    /**
     * Restore password by change it to autogenerated one. Then send email with new password.
     *
     * <p>Check if user with <b>email</b> exist. If not execution is interrupted.</p>
     * <p>Set activation status to <tt>true</tt> and delete activationCode if exist.</p>
     * <p>Generate new password and set it to User. Then send email with new password.</p>
     *
     * @param email
     * @return <tt>true</tt> - if user with <b>email</b> exist and password restored. <tt>false</tt> - if user with <b>email</b> doesn't exist.
     */
    public boolean restorePassword(String email) {
        if (!userRepo.existsUserByEmail(email)) {
            LOGGER.info("Reset password failed, email:{} not found in DB.", email);
            return false;
        }

        User userFromDb = userRepo.findByEmail(email);
        if (activationCodeRepo.findByUser(userFromDb).isPresent()) {
            activationCodeRepo.deleteByUser(userFromDb);
            userFromDb.setActivated(true);
        }

        String password = RandomStringUtils.randomAlphanumeric(8);
        userFromDb.setPassword(bCryptPasswordEncoder.encode(password));
        userRepo.save(userFromDb);

        String message = String.format("Здравствуйте, %s! \n + Ваш новый пароль: " + "%s", userFromDb.getFirstName(), password);
        emailSender.send(userFromDb.getEmail(), "Восстановление пароля", message);

        LOGGER.info("Password for User:{} restored.", userFromDb.getEmail());
        return true;
    }

    public void updatePrincipal() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User oldUser = (User) auth.getPrincipal();
        User userFromDB = userRepo.findByEmail(oldUser.getEmail());
        Authentication newAuth = new UsernamePasswordAuthenticationToken(userFromDB, userFromDB.getPassword(), userFromDB.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }

    public boolean changePassword(String oldPass, String newPass) {
//        String encodeOldPass = bCryptPasswordEncoder.encode(oldPass);
        String encodeNewPass = bCryptPasswordEncoder.encode(newPass);
        User userFromSecurityContext = getUserFromPrincipal();
        User userFromDb = userRepo.findByEmail(userFromSecurityContext.getEmail());

        if (!bCryptPasswordEncoder.matches(oldPass, userFromDb.getPassword())) {
            LOGGER.warn("Password from DB not equal password from principal for User:{}", userFromDb.getEmail());
            return false;
        }

        userFromDb.setPassword(encodeNewPass);
        userRepo.save(userFromDb);
        LOGGER.info("User:{} successfully change password.", userFromDb.getEmail());
        return true;
    }

    public static User getUserFromPrincipal() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public Set<Role> getAllRoles() {
        return new HashSet<>(roleRepo.findAll());
    }

    public boolean isEmailUsed(String email) {
        return userRepo.existsUserByEmail(email);
    }
}
