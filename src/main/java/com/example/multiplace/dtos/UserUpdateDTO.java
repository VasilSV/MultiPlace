package com.example.multiplace.dtos;

public class UserUpdateDTO {
    private String email;
    private String newIdentificationNumber;
    private String newPassword;

    public UserUpdateDTO() {
    }

    public String getEmail() {
        return email;
    }

    public UserUpdateDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getNewIdentificationNumber() {
        return newIdentificationNumber;
    }

    public void setNewIdentificationNumber(String newIdentificationNumber) {
        this.newIdentificationNumber = newIdentificationNumber;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
