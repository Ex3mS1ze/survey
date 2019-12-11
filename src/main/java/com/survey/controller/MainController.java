package com.survey.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class MainController {

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(value = "error", required = false) String error,
                               Principal principal, Model model) {
        if (error == null) {
            if (principal == null)
                return "login";
            else
                return "redirect:/";
        }

        model.addAttribute("loginDataError", "Неверное имя пользователя или пароль");
        return "login";
    }

}
