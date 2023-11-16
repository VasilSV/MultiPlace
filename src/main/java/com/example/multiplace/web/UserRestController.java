package com.example.multiplace.web;

import com.example.multiplace.dtos.UserDTO;
import com.example.multiplace.service.UserEntityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
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
            // Логика за обработка на грешките, например:
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}