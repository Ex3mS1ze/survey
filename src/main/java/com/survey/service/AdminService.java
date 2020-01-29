package com.survey.service;

import com.survey.entity.User;
import com.survey.repository.UserRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AdminService {
    private static final Logger LOGGER = LogManager.getLogger(UserService.class.getName());

    private final UserRepo userRepo;

    @Autowired
    public AdminService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User saveUserChanges(User user){
        User admin = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userFromDb = userRepo.findById(user.getId()).orElseThrow(NoSuchElementException::new);

        userFromDb.setEmail(user.getEmail());
        userFromDb.setRoles(user.getRoles());
        userFromDb.setFirstName(user.getFirstName());
        userFromDb.setSecondName(user.getSecondName());
        userFromDb.setGender(user.getGender());
        userFromDb.setPhoneNumber(user.getPhoneNumber());
        userFromDb.setActivated(user.getActivated());
        LOGGER.info("Personal data of User: {} changed by Admin: id={}, email={}.", userFromDb.getEmail(), admin.getId(), admin.getEmail());
        return userRepo.save(userFromDb);

    }

}
