package com.example.multiplace.view;

public class UserProfileView {

    private String username;

    private String identificationNumber;

    private String email;

    private String role;

    public UserProfileView(String username, String identificationNumber, String email, String role) {
        this.username = username;
        this.identificationNumber = identificationNumber;
        this.email = email;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public UserProfileView setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public UserProfileView setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserProfileView setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getRole() {
        return role;
    }

    public UserProfileView setRole(String role) {
        this.role = role;
        return this;
    }
}
