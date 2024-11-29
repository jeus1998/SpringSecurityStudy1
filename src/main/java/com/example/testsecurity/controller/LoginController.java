package com.example.testsecurity.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {
    private final SecurityContextLogoutHandler securityContextLogoutHandler;
    @GetMapping("/loginPage")
    public String loginPage(){
        return "login";
    }
    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response){
        log.info("logout");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        securityContextLogoutHandler.logout(request, response, authentication);
        return "redirect:/";
    }
}
