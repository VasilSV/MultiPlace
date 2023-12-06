package com.example.multiplace.dtos;

import com.example.multiplace.model.entity.UserRoleEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserDTO {

    private Long id;
    @NotNull
    private String username;
    @NotNull
    private String identificationNumber;
    @NotNull
    private String email;
    @NotNull
    private UserRoleEntityDTO role;

    public UserDTO() {
    }

    public Long getId() {
        return id;
    }

    public UserDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public UserDTO setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserRoleEntityDTO getRole() {
        return role;
    }

    public UserDTO setRole(UserRoleEntityDTO role) {
        this.role = role;
        return this;
    }
}
