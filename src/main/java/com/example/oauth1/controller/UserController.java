package com.example.oauth1.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

@RestController
public class UserController {

    @Autowired
    WebApplicationContext webApplicationContext;

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

    @GetMapping("/api/pub")
    public String pub() {
        return "pub";
    }

    @GetMapping("/b/api/me")
    public String me1() {
        return "me";
    }

    @GetMapping("/b/api/basic")
    public String basic1() {
        return "basic";
    }

    @GetMapping("/filter")
    public String filter(HttpServletRequest request) {
        FilterChainProxy proxy = webApplicationContext.getBean("springSecurityFilterChain", FilterChainProxy.class);
        proxy.getFilterChains().forEach( t -> {
            t.matches(request);
            t.getFilters().forEach(f -> System.out.println(f.getClass()));
            System.out.println("------------------------------");
        });
        return "filter";
    }
}
