package com.example.multiplace.web;


import com.example.multiplace.model.CurrentUser;

import com.example.multiplace.repository.UserRepository;
import com.example.multiplace.service.AppUserDetailsService;
import com.example.multiplace.service.InitService;
import com.example.multiplace.view.UserProfileView;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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

    @ModelAttribute("userProfileView")
    public UserProfileView initUserProfileView() {
        return new UserProfileView();
    }

    @GetMapping("/pages/company")
    public String companyPage(Model model,
                              @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails instanceof CurrentUser) {
            CurrentUser currentUser = (CurrentUser) userDetails;
            model.addAttribute("identificationNumber", currentUser.getIdentificationNumber());

            model.addAttribute("email", currentUser.getEmail());
        }


        String userEmail = userDetails.getUsername();


        UserProfileView userProfileView = fetchUserProfile(userEmail);


        model.addAttribute("userProfileView", userProfileView);
        model.addAttribute("identificationNumber", userProfileView.getIdentificationNumber());
        model.addAttribute("username", userProfileView.getUsername());


        return "company";
    }

    private UserProfileView fetchUserProfile(String email) {

        UserDetails userDetails = appUserDetailsService.loadUserByUsername(email);


        UserProfileView userProfileView = mapUserDetailsToUserProfileView(userDetails);

        return userProfileView;
    }

    private UserProfileView mapUserDetailsToUserProfileView(UserDetails userDetails) {


        UserProfileView userProfileView = new UserProfileView();
        userProfileView.setUsername(userDetails.getUsername());
        //userProfileView.setIdentificationNumber()
        // Други полета...

        return userProfileView;
    }

}
