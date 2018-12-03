package com.example.oauth1.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/api/me")
    public String profile() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        return "me";
    }

    @GetMapping("/api/basic")
    public String basic() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        return "basic";
    }
}
