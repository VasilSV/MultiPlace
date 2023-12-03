package com.example.multiplace.web;

import com.example.multiplace.model.entity.UserEntity;
import com.example.multiplace.repository.UserRepository;
import com.example.multiplace.service.AppUserDetailsService;
import com.example.multiplace.service.InitService;
import com.example.multiplace.view.UserProfileView;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.NoSuchElementException;
import java.util.Optional;

@CrossOrigin("*")
@Controller
public class PagesController {
    private final AppUserDetailsService appUserDetailsService;
    private final UserRepository userRepository;
    private final InitService initService;


    public PagesController(AppUserDetailsService appUserDetailsService, UserRepository userRepository, InitService initService) {
        this.appUserDetailsService = appUserDetailsService;
        this.userRepository = userRepository;

        this.initService = initService;
    }

    @GetMapping("/")
    public String index(HttpSession httpSession, Model model) {

        if (httpSession.getAttribute("user") == null) {

            return "index";
        }
        return "all";
    }

    @GetMapping("/pages/all")
    public String all() {
        return "all";
    }

    @GetMapping("/pages/admins")
    public String admins() {
        return "admins";
    }

    @GetMapping("/pages/users")
    public String users() {
        return "users";
    }
    @GetMapping("/orders/by/all")
    public String dostavka() {
        return "dostavka";
    }

    @GetMapping("/pages/company")
    public String company() {
        return "company";
    }

    @GetMapping("/users/profile")
    public String profiles() {
        return "profile";
    }

    @GetMapping("/product/coser")
    public String coser() {
        return "coser";
    }

    @GetMapping("/product/helmet")
    public String helmet() {
        return "helmet";
    }

    @GetMapping("/product/hammer")
    public String hammer() {
        return "hammer";
    }

    @GetMapping("/product/shovel")
    public String shovel() {
        return "shovel";
    }

    @GetMapping("/product/gloves")
    public String gloves() {
        return "gloves";
    }

    @GetMapping("/product/magnetic-hammer")
    public String magneticHammer() {
        return "magnetic-hammer";
    }



}
