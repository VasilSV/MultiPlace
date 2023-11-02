package com.example.multiplace.web;

import com.example.multiplace.dtos.UserRegistrationDTO;
import com.example.multiplace.service.UserEntityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/users")
@Controller
public class UserRegistrationController {

    private UserEntityService userEntityService;

    public UserRegistrationController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }



    @GetMapping("/register")
    public String register() {
        return "auth-register";
    }

    @PostMapping("/register")
    public String register(UserRegistrationDTO userRegistrationDTO) {
        return null;

    }
}