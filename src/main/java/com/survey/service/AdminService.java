package com.survey.service;

import com.survey.entity.User;
import com.survey.repository.UserRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    private static final Logger LOGGER = LogManager.getLogger(UserService.class.getName());

    @Autowired
    private UserRepo userRepo;

    public void saveUserChanges(User user){
        User admin = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> userFromDb = userRepo.findById(user.getId());
        if (!userFromDb.isPresent()) {
            return;
        }

        userFromDb.get().setEmail(user.getEmail());
        userFromDb.get().setRoles(user.getRoles());
        userFromDb.get().setFirstName(user.getFirstName());
        userFromDb.get().setSecondName(user.getSecondName());
        userFromDb.get().setGender(user.getGender());
        userFromDb.get().setPhoneNumber(user.getPhoneNumber());
        userFromDb.get().setActivated(user.getActivated());
        userRepo.save(userFromDb.get());

        LOGGER.info("Personal data of User: {} changed by Admin: id={}, email={}.", userFromDb.get().getEmail(), admin.getId(), admin.getEmail());
    }

}
