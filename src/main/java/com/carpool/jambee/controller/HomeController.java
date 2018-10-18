package com.carpool.jambee.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/index", "/home"})
    public String index(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) { // If someone is logged in
            model.addAttribute("isLoggedIn", true);
        } else { // If no one is logged in (also with default name "anonymousUser")
            model.addAttribute("isLoggedIn", false);
        }

        return "index";
    }

    @GetMapping("/login")
    public String login() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails)
            return "redirect:/home";

        return "login";
    }

    @GetMapping("/test")
    public String test() {
        return "test_authentication";
    }
}
