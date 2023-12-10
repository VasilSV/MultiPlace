package com.example.multiplace.service;

import com.example.multiplace.model.entity.ToolEntity;
import com.example.multiplace.model.entity.UserEntity;
import com.example.multiplace.model.entity.UserRoleEntity;
import com.example.multiplace.model.enums.UserRoleEnum;
import com.example.multiplace.model.enums.UserTypeEntity;
import com.example.multiplace.repository.ToolEntityRepository;
import com.example.multiplace.repository.UserRepository;
import com.example.multiplace.repository.UserRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.example.multiplace.model.enums.UserRoleEnum.ADMIN;
import static com.example.multiplace.model.enums.UserRoleEnum.COMPANY;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InitServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserRoleRepository userRoleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private ToolEntityRepository toolEntityRepository;

    @InjectMocks
    private InitService initService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void initRoles_WhenNoRolesExist_ShouldCreateRoles() {
        when(userRoleRepository.count()).thenReturn(0L);

        initService.initRolesWrapper();

        verify(userRoleRepository, times(2)).save(any(UserRoleEntity.class));
    }

    @Test
    void initRoles_WhenRolesExist_ShouldNotCreateRoles() {
        when(userRoleRepository.count()).thenReturn(2L);

        initService.initRolesWrapper();

        verify(userRoleRepository, never()).save(any(UserRoleEntity.class));

    }

    @Test
    void initRolesWrapper_WhenNoRolesExist_ShouldCreateRoles() {
        when(userRoleRepository.count()).thenReturn(0L);

        initService.initRolesWrapper();

        verify(userRoleRepository, times(2)).save(any(UserRoleEntity.class));
    }

    @Test
    void initAdmin_ShouldCreateAdminUser() {
        when(userRoleRepository.findUserRoleEntityByRole(ADMIN)).thenReturn(Optional.of(new UserRoleEntity()));
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        initService.initRolesWrapper();

        verify(userRepository, times(0)).save(any(UserEntity.class));
    }

    @Test
    void initCompany_ShouldCreateCompanyUser() {
        when(userRoleRepository.findUserRoleEntityByRole(COMPANY)).thenReturn(Optional.of(new UserRoleEntity()));
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        initService.initRolesWrapper();

        verify(userRepository, times(0)).save(any(UserEntity.class));
    }

    @Test
    void initNormalUser_ShouldCreateNormalUser() {
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        initService.initRolesWrapper();

        verify(userRepository, times(0)).save(any(UserEntity.class));
    }

    @Test
    void initTools_WhenNoToolsExist_ShouldCreateTools() {
        when(toolEntityRepository.count()).thenReturn(0L);

        initService.initToolsWrapper();

        verify(toolEntityRepository, times(1)).saveAll(anyList());
    }

    @Test
    void initTools_WhenToolsExist_ShouldNotCreateTools() {
        when(toolEntityRepository.count()).thenReturn(3L);

        initService.initToolsWrapper();

        verify(toolEntityRepository, never()).saveAll(anyList());
    }


}