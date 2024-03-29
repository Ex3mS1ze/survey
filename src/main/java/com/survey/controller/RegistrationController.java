package com.survey.controller;

import com.survey.dto.CaptchaResponseDto;
import com.survey.entity.User;
import com.survey.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Collections;

@Controller
public class RegistrationController {
    private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";
    private final static Logger LOGGER = LogManager.getLogger(RegistrationController.class.getName());

    private final UserService userService;

    private final RestTemplate restTemplate;

    @Value("${recaptcha.secret}")
    private String secret;

    @Autowired
    public RegistrationController(UserService userService, RestTemplate restTemplate) {
        this.userService = userService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String handleRegistrationForm(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult,
                                         @RequestParam("role") String userRole,
                                         @RequestParam("g-recaptcha-response") String captchaResponse, Model model) {

        String url = String.format(CAPTCHA_URL, secret, captchaResponse);
        CaptchaResponseDto response = restTemplate.postForObject(url, Collections.emptyList(),
                                                                 CaptchaResponseDto.class);

        if (!response.isSuccess()) {
            model.addAttribute("captchaError", "Заполните капчу");
        }

        if (bindingResult.hasErrors() || !response.isSuccess()) {
            return "registration";
        }

        if (!userService.saveNewUser(userForm, userRole)) {
            LOGGER.warn("User wasn't save. Mb email already used.");
            return "registration";

        }

        User newUser = (User) userService.loadUserByUsername(userForm.getEmail());
        userService.createAndSendActivationCode(newUser);
        model.addAttribute("emailToActivate", newUser.getEmail());
        return "tip-activate";
    }

    @GetMapping("/activate/{code}")
    public String activateUser(Model model, @PathVariable String code) {
        if (code == null || code.trim().isEmpty()) {
            return "redirect:/login";
        }

        boolean isActivated = userService.activateUser(code.trim());
        if (!isActivated) {
            model.addAttribute("activationStatus", "Произошла ошибка");
            LOGGER.warn("Activation failed.");
        }

        model.addAttribute("activationStatus", "Ваш аккауант активирован");
        return "login";
    }

    @GetMapping("/restore_password")
    public String getRestorePasswordPage(Model model) {
        model.addAttribute("userForm", new User());
        return "restorepassword";
    }

    @PostMapping("/restore_password")
    public String restorePassword(@ModelAttribute("userForm") User userFrom, Model model) {
        userService.restorePassword(userFrom.getEmail());
        return "index";
    }

    @GetMapping("/search_used_mail")
    public ResponseEntity<?> isEmailUsed(@RequestParam(value = "enteredEmail") String email) {
        boolean emailUsed = userService.isEmailUsed(email);
        return ResponseEntity.ok(emailUsed);
    }

}
