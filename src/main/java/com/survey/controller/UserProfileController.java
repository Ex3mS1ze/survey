package com.survey.controller;

import com.survey.entity.Patient;
import com.survey.entity.User;
import com.survey.service.DoctorService;
import com.survey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;

@Controller
public class UserProfileController {
    private final UserService userService;
    private final DoctorService doctorService;

    @Autowired
    public UserProfileController(UserService userService, DoctorService doctorService) {
        this.userService = userService;
        this.doctorService = doctorService;
    }

    @GetMapping("/profile")
    public String getProfilePage(Model model) {
        if (model.containsAttribute("userForm")) {
            return "profile";
        }

        User user = UserService.getUserFromPrincipal();
        if (user.isPatient()) {
            Patient patient = doctorService.getPatientByUser(user);
            model.addAttribute("doctor", patient.getDoctor());
        }
        model.addAttribute("userForm", user);
        return "profile";
    }

    @PostMapping("/editPersonalData")
    public String editPersonalData(@Validated @ModelAttribute("userForm") User userForm, BindingResult bindingResult,
                                   HttpServletResponse response, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasFieldErrors("firstName") || bindingResult.hasFieldErrors("secondName") ||
            bindingResult.hasFieldErrors("phoneNumber")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userForm",
                                                 bindingResult);
            redirectAttributes.addFlashAttribute("userForm", userForm);
            return "redirect:/profile";
        }
        userService.editUserData(userForm);
        userService.updatePrincipal();
        return "redirect:/profile";
    }

    @PostMapping("/changePassword")
    public String changePassword(@Validated @ModelAttribute("userForm") User userForm, BindingResult bindingResult,
                                 Model model, HttpServletResponse response) {
        if (bindingResult.hasFieldErrors("oldPassword") || bindingResult.hasFieldErrors("password") ||
            bindingResult.hasFieldErrors("passwordConfirm") ||
            !userService.changePassword(userForm.getOldPassword(), userForm.getPassword())) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "redirect:/profile";
        }

        userService.updatePrincipal();
        userService.removeAuthentication();
        return "redirect:/login";
    }
}
