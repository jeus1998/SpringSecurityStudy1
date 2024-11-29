package com.example.testsecurity.controller;


import com.example.testsecurity.dto.JoinDTO;
import com.example.testsecurity.service.JoinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class JoinController {
    private final JoinService joinService;
    @GetMapping("/join")
    public String join(){
        return "join";
    }
    @PostMapping("/joinProc")
    public String joinProcess(@ModelAttribute JoinDTO joinDTO){
        log.info("joinDTO={}", joinDTO);

        joinService.joinProcess(joinDTO);

        return "redirect:/login";
    }
}