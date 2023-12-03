package com.example.multiplace.web;

import com.example.multiplace.dtos.UserDTO;
import com.example.multiplace.model.entity.UserEntity;
import com.example.multiplace.repository.UserRepository;
import com.example.multiplace.service.UserEntityService;
import com.example.multiplace.view.UserProfileView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/users")
public class UserRestController {
    private UserEntityService userEntityService;
    private UserRepository userRepository;

    public UserRestController(UserEntityService userEntityService, UserRepository userRepository) {
        this.userEntityService = userEntityService;
        this.userRepository = userRepository;
    }


    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userEntityService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
        Optional<UserDTO> userDTOOptional = userEntityService.findUserById(id);
        if (!userDTOOptional.isPresent()){
            throw new NoSuchElementException();
        }
        return userDTOOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> deleteUserById(@PathVariable("id")Long id){
        System.out.println("neeeeee");
        try {
            userEntityService.deleteUserById(id);
            return ResponseEntity.noContent().build();

        } catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/users/profile")

    public String profile(@AuthenticationPrincipal UserDetails userDetails,
                          Model model) {

        Optional<UserEntity> user = userRepository.
                //todo не е уникално...
                        findByUsername(userDetails.getUsername());
        if (user.isPresent()) {
            UserProfileView profileView = new UserProfileView(
                    user.get().getUsername(),
                    user.get().getIdentificationNumber(),
                    user.get().getEmail(),
                    user.get().getRoles() == null ? "USER" : String.valueOf(user.get().getRoles()));

            model.addAttribute("user", profileView);
            System.out.println("ProfileView: " + profileView);
        } else {
            model.addAttribute("errorMessage", "Потребителят не е намерен");
            return "error";
        }
        return "profile";
    }

}