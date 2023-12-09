package com.example.multiplace.web;

import com.example.multiplace.dtos.UserUpdateDTO;
import com.example.multiplace.service.UserEntityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/company")
public class UserUpdateRestController {

    private final UserEntityService userEntityService;

    public UserUpdateRestController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }

    //    @PostMapping("/update-profile")
//    public ResponseEntity<String> updateProfile(
//            @RequestParam String email,
//            @RequestParam(required = false) String newPassword,
//            @RequestParam(required = false) String newIdentificationNumber) {
//
//        userEntityService.updateUserProfile(email, newPassword, newIdentificationNumber);
//        return ResponseEntity.ok("User profile updated successfully!");
//    }
    @PostMapping("/update-profile")
    public ResponseEntity<Map<String, String>> updateUserProfile(
            @RequestBody UserUpdateDTO userUpdateDTO) {


        Map<String, String> response = new HashMap<>();
        response.put("message", "User profile updated successfully!");
        return ResponseEntity.ok(response);
    }
}

