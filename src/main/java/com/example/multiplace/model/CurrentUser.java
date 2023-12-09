package com.example.multiplace.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CurrentUser extends User {

    private String username;

    private String email;
    private String identificationNumber;

    public CurrentUser(String username, String password, Collection<? extends GrantedAuthority> authorities,
                       String email, String identificationNumber) {
        super(username, password, authorities);
        this.email = email;
        this.identificationNumber = identificationNumber;
    }

    public String getEmail() {
        return email;
    }

    public CurrentUser setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public CurrentUser setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public CurrentUser setUsername(String username) {
        this.username = username;
        return this;
    }
}
