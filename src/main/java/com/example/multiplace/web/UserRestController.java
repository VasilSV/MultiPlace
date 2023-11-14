package com.example.multiplace.web;

import com.example.multiplace.dtos.UserDTO;
import com.example.multiplace.service.UserEntityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/users")
public class UserRestController {
    private UserEntityService userEntityService;

    public UserRestController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }


    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userEntityService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
        Optional<UserDTO> userDTOOptional = userEntityService.findUserById(id);

        return userDTOOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> deleteUserById(@PathVariable("id")Long id){

        userEntityService.deleteUserById(id);

        return ResponseEntity.
                noContent().
                build();
    }
}