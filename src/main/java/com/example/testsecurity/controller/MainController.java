package com.example.testsecurity.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class MainController {
    @GetMapping("/")
    public String mainPage(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> authorityList = new ArrayList<>();
        for (GrantedAuthority authority : authorities){
            authorityList.add(authority.getAuthority());
        }

        model.addAttribute("id", userId);
        model.addAttribute("role", authorityList);

        return "main";
    }
}
