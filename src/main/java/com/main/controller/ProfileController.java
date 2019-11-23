package com.main.controller;

import com.main.entity.User;
import com.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class ProfileController {
    @Autowired
    UserService userService;

    @GetMapping("/profile")
    public String getLoginPage(Authentication authentication, RedirectAttributes redirectAttrs, Model model) {
        User user = (User) authentication.getPrincipal();
        model.addAttribute("userForm", user);
        return "profile";
    }

    @PostMapping("/editPersonalData")
    public String editPersonalData(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userService.editUserData(userForm);
        if (bindingResult.hasErrors()) {
            return "profile";
        }
        return "redirect:/profile";
    }
}
