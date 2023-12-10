package com.example.multiplace.service;

import com.example.multiplace.dtos.UserDTO;
import com.example.multiplace.dtos.UserRegistrationDTO;
import com.example.multiplace.model.entity.UserEntity;
import com.example.multiplace.model.entity.UserRoleEntity;
import com.example.multiplace.model.enums.UserRoleEnum;
import com.example.multiplace.repository.UserRepository;
import com.example.multiplace.repository.UserRoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.core.Authentication;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.example.multiplace.model.enums.UserRoleEnum.COMPANY;
import static com.example.multiplace.model.enums.UserTypeEntity.BULSTAT;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserEntityServiceTest {


    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private UserRoleRepository userRoleRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserEntityService userEntityService;
    @InjectMocks
    private AppUserDetailsService appUserDetailsService;

    @Test
    void registerUser_ShouldRegister() {
        // Arrange
        UserRegistrationDTO registrationDTO = new UserRegistrationDTO();
        registrationDTO.setUsername("testUser");
        registrationDTO.setUserTypeEntity(BULSTAT);
        registrationDTO.setIdentificationNumber("12345");
        registrationDTO.setEmail("test@example.com");
        registrationDTO.setPassword("password");

        when(userRoleRepository.findUserRoleEntityByRole(COMPANY)).thenReturn(Optional.of(new UserRoleEntity()));
        when(passwordEncoder.encode(registrationDTO.getPassword())).thenReturn("encodedPassword");

        // Act
        userEntityService.registerUser(registrationDTO, authentication -> {
        });

        // Assert
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = {"ROLE_COMPANY"})
    void registerUser_ShouldInvokeSuccessfulLogin() {
        // Arrange
        UserRegistrationDTO registrationDTO = new UserRegistrationDTO();
        registrationDTO.setUsername("testUser");
        registrationDTO.setUserTypeEntity(BULSTAT);
        registrationDTO.setIdentificationNumber("12345");
        registrationDTO.setEmail("test@example.com");
        registrationDTO.setPassword("password");
//        registrationDTO.setRole(List.of(userRoleRepository.findAllByRoleIn(List.of(COMPANY))
//                .stream()
//                .findFirst()
//                .orElseThrow()));

        when(userRoleRepository.findUserRoleEntityByRole(COMPANY)).thenReturn(Optional.of(new UserRoleEntity()));
        when(passwordEncoder.encode(registrationDTO.getPassword())).thenReturn("encodedPassword");


        AuthenticationProcessor authenticationProcessor = mock(AuthenticationProcessor.class);
        userEntityService.registerUser(registrationDTO, authenticationProcessor::processAuthentication);


        verify(authenticationProcessor, times(1)).processAuthentication(any());
    }

    @Test
    void getAllUsers_ShouldReturnListOfUser() {

        when(userRepository.findAll()).thenReturn(Collections.singletonList(new UserEntity()));


        List<UserDTO> userDTOs = userEntityService.getAllUsers();

        assertFalse(userDTOs.isEmpty());
    }

    @Test
    void findUserById_WhenUserExists_ReturnUserDTO() {

        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.of(new UserEntity()));


        Optional<UserDTO> userDTO = userEntityService.findUserById(userId);


        assertTrue(userDTO.isPresent());
    }

    @Test
    void findUserById_WhenUserDoesNotExist() {

        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());


        Optional<UserDTO> userDTO = userEntityService.findUserById(userId);


        assertTrue(userDTO.isEmpty());
    }

    @Test
    void deleteUserById_ShouldDelete() {
        // Arrange
        Long userId = 1L;

        // Act
        userEntityService.deleteUserById(userId);

        // Assert
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void loadUserByUsername_WhenUserExists_ShouldReturnUserDetails() {
        // Arrange
        String email = "test@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(new UserEntity()));

        // Act
        UserDetails userDetails = appUserDetailsService.loadUserByUsername(email);

        // Assert
        assertNotNull(userDetails);
    }

    @Test
    void loadUserByUsername_WhenUserDoesNotExist_ShouldThrowException() {
        // Arrange
        String email = "az@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () ->
                appUserDetailsService.loadUserByUsername(email));
    }

    interface AuthenticationProcessor {
        void processAuthentication(Authentication authentication);
    }
}