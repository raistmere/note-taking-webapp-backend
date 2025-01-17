package com.raistmere.notetakingwebapp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class HomeController {

    @GetMapping("/")
    public String getHome() {

        return "Welcome to the Home Page";
    }

    @GetMapping("/dashboard")
    public String getDashboard(Authentication authentication) {

        String username = authentication.getName();

        return "Welcome to your Dashboard, " + username;
    }
}
