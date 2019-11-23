package com.main.service;

import com.main.entity.User;
import com.main.repository.RoleRepo;
import com.main.repository.UserRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Service
public class UserService implements UserDetailsService {
    private static final Logger LOGGER = LogManager.getLogger(UserService.class.getName());

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
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

    public void saveNewUser(User user) {
        //TODO change to email confirmation
        user.setActivated(true);
        user.setRoles(roleRepo.findByRolename("ROLE_USER"));
        user.setRegistrationDate(LocalDateTime.now());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if (user.checkMandatoryFields() && !userRepo.existsUserByEmail(user.getEmail())){
            userRepo.save(user);
            return;
        }
        LOGGER.warn("SaveNewUser rejected. User with same email {} exist or not all fields fulfilled", user.getEmail());
    }

    public void editUserData(User user) {
        User userFromSecurityContext = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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

}
