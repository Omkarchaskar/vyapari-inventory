package com.vyapari.inventorymanagement.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final String ADMIN_USERNAME = "admin";
    private final String ADMIN_PASSWORD = "12345";

    // Show login page
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // Handle login form
    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {

            // 🔥 SET SESSION VALUE
            session.setAttribute("loggedInUser", username);

            return "redirect:/dashboard";
        }

        model.addAttribute("error", "Invalid Username or Password");
        return "login";
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();     // 🔥 CLEAR SESSION
        return "redirect:/login";
    }
}
