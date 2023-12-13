package com.example.multiplace.service;

import com.example.multiplace.model.entity.UserEntity;
import com.example.multiplace.model.entity.UserRoleEntity;
import com.example.multiplace.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppUserDetailsServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private AppUserDetailsService userDetailsService;

    @Test
    void loadUserByUsername_UserNotFound_ThrowsUsernameNotFoundException() {

        String userEmail = "nonexistent@example.com";
        when(userRepository.findByEmail(userEmail)).thenReturn(Optional.empty());


        assertThrows(UsernameNotFoundException.class,
                () -> userDetailsService.loadUserByUsername(userEmail));
    }

    @Test
    void loadUserByUsername_UserFound_ReturnsUserDetails() {
        // Arrange
        String userEmail = "tets@abv.bg";
        UserEntity mockUserEntity = new UserEntity();
        mockUserEntity.setEmail(userEmail);
        mockUserEntity.setPassword("qqq");


        when(userRepository.findByEmail(userEmail)).thenReturn(Optional.of(mockUserEntity));


        UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);


        assertNotNull(userDetails);
        assertEquals(userEmail, userDetails.getUsername());
        assertEquals("qqq", userDetails.getPassword());

    }

}
