package com.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainController {

    @GetMapping("/login")
    public String getLoginPage(Principal principal) {
        return principal == null ? "login" : "redirect:/";
    }

}
