package com.example.multiplace.web;

import com.example.multiplace.dtos.UserRegistrationDTO;
import com.example.multiplace.service.UserEntityService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/users")
@Controller
public class UserRegistrationController {
    private final SecurityContextRepository securityContextRepository;
    private UserEntityService userEntityService;

    public UserRegistrationController(SecurityContextRepository securityContextRepository, UserEntityService userEntityService) {
        this.securityContextRepository = securityContextRepository;
        this.userEntityService = userEntityService;
    }


    @GetMapping("/register")
    public String register() {
        return "auth-register";
    }
//
//    @PostMapping("/register")
//    public String registerNewUser(
//            UserRegistrationDTO userRegistrationDTO,
//            HttpServletRequest request,
//            HttpServletResponse response) {
//
//        userEntityService.registerUser(userRegistrationDTO, successfulAuth -> {
//            SecurityContextHolderStrategy strategy = SecurityContextHolder.getContextHolderStrategy();
//
//            SecurityContext context = strategy.createEmptyContext();
//            context.setAuthentication(successfulAuth);
//
//            strategy.setContext(context);
//
//            securityContextRepository.saveContext(context, request, response);
//        });
//
//        return "redirect:/";
    //}

    @PostMapping("/register")
    private String registerUser(@Valid @ModelAttribute(name = "userRegistrationDto") UserRegistrationDTO userRegistrationDTO,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes
    ) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("userRegistrationDto", userRegistrationDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDto", bindingResult);

            return "redirect:/users/register";
        }
        this.userEntityService.registerUser(userRegistrationDTO);

        return "redirect:/";
    }

    @PostMapping ("/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/";

    }

    @ModelAttribute("userRegistrationDTO")
    public UserRegistrationDTO initRegistrationDTO() {
        return new UserRegistrationDTO();
    }
}