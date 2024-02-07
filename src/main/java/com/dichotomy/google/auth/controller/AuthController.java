package com.dichotomy.google.auth.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

public class AuthController {


    @GetMapping("/auth/google")
    public String handleGoogleAuth() {
        // Logic to handle Google authentication
        return "Google authentication successful!";
    }

    @GetMapping("/auth/facebook")
    public String handleFacebookAuth() {
        // Logic to handle Facebook authentication
        return "Facebook authentication successful!";
    }
}
