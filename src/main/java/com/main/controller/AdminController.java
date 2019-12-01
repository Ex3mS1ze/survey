package com.main.controller;

import com.main.entity.Role;
import com.main.entity.User;
import com.main.service.AdminService;
import com.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class AdminController {
    @Autowired
    UserService userService;
    @Autowired
    AdminService adminService;

    @GetMapping("/admin/users")
    public String getAdminPage(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin";

    }
    @GetMapping("/admin/edit_user")
    public String getEditUserPage(@RequestParam(value = "userId", required = false) Long userId, Model model) {
        User user = userService.getUserById(userId);
        Set<Role> roles = userService.getAllRoles();
        model.addAttribute("allRoles", roles);
        model.addAttribute("user", user);
        return "admin-edit-users";
    }

    @PostMapping("/save_user_changes")
    public String saveUserProfileChanges(@ModelAttribute(value = "user") User user, Model model) {
        //TODO validation
        adminService.saveUserChanges(user);
        return "redirect:/admin/users";
    }
}
