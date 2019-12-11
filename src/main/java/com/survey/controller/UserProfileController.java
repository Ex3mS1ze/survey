package com.survey.controller;

import com.survey.entity.Patient;
import com.survey.entity.Role;
import com.survey.entity.User;
import com.survey.service.DoctorService;
import com.survey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserProfileController {
    @Autowired
    UserService userService;
    @Autowired
    DoctorService doctorService;

    @GetMapping("/profile")
    public String getProfilePage(Authentication authentication, RedirectAttributes redirectAttrs, Model model) {
        User user = UserService.getUserFromPrincipal();
        if (Role.isRolesContainsByRolename(user.getRoles(), "PATIENT")) {
            Patient patient = doctorService.getPatientByUser(user);
            model.addAttribute("doctor", patient.getDoctor());
        }
        model.addAttribute("userForm", user);
        return "profile";
    }

    @PostMapping("/editPersonalData")
    public String editPersonalData(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userService.editUserData(userForm);
        //TODO valid
        if (bindingResult.hasErrors()) {
            return "profile";
        }
        userService.updatePrincipal();
        return "redirect:/profile";
    }

    @PostMapping("/changePassword")
    public String changePassword(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        //TODO valid
        if (!userService.changePassword(userForm.getOldPassword(), userForm.getPassword())) {
            return "redirect:/profile";
        }
        userService.updatePrincipal();
        return "login";
    }
}
