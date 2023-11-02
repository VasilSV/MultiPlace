package com.example.multiplace.service;

import com.example.multiplace.dtos.UserRegistrationDTO;
import com.example.multiplace.model.entity.UserEntity;
import com.example.multiplace.repository.UserRepository;
import com.example.multiplace.repository.UserRoleRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserEntityService {
    private final UserDetailsService userDetailsService;
    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserEntityService(UserDetailsService userDetailsService, UserRoleRepository userRoleRepository,
                             UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public void registerUser(
            UserRegistrationDTO userRegistrationDTO) {

        userRepository.save(map(userRegistrationDTO));
    }

    private UserEntity map(UserRegistrationDTO userRegistrationDTO) {
        return new UserEntity()
//                .setActive(true)
                .setUsername(userRegistrationDTO.getUsername())
                .setIdentificationNumber(userRegistrationDTO.getIdentificationNumber())
                .setEmail(userRegistrationDTO.getEmail())
                .setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
    }
}

