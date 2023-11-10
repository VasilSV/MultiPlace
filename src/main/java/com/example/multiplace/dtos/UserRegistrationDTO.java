package com.example.multiplace.dtos;

import com.example.multiplace.model.enums.UserRoleEnum;
import com.example.multiplace.model.enums.UserTypeEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRegistrationDTO {

    //    @FieldMatch(
//            first = "password",
//            second = "confirmPassword",
//            message = "Passwords should match."
//    )
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters ")
    @NotNull
    private String username;
    @NotNull
    private String identificationNumber;
    @NotNull
    private UserTypeEntity userTypeEntity;
    @Email
    @NotBlank(message = "Email cannot be empty!")
    private String email;
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters ")
    @NotNull
    private String password;
    @Size(min = 3, max = 20, message = " ")
    @NotNull
    private String confirmPassword;

    private UserRoleEntityDTO role;

    public UserRegistrationDTO() {
    }

    public UserTypeEntity getUserTypeEntity() {
        return userTypeEntity;
    }

    public UserRegistrationDTO setUserTypeEntity(UserTypeEntity userTypeEntity) {
        this.userTypeEntity = userTypeEntity;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserRegistrationDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserRoleEntityDTO getRole(UserRoleEnum company) {
        return role;
    }

    public UserRegistrationDTO setRole(UserRoleEntityDTO role) {
        this.role = role;
        return this;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public UserRegistrationDTO setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegistrationDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegistrationDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegistrationDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    @Override
    public String toString() {
        return "UserRegistrationDTO{" +
                "username='" + username + '\'' +
                ", identificationNumber='" + identificationNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
