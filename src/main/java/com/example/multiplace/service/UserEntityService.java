package com.example.multiplace.service;

import com.example.multiplace.dtos.UserDTO;
import com.example.multiplace.dtos.UserRegistrationDTO;
import com.example.multiplace.dtos.UserRoleEntityDTO;
import com.example.multiplace.model.entity.UserEntity;
import com.example.multiplace.model.entity.UserRoleEntity;
import com.example.multiplace.model.enums.UserTypeEntity;
import com.example.multiplace.repository.UserRepository;
import com.example.multiplace.repository.UserRoleRepository;
import com.example.multiplace.service.exception.EmailAlreadyExistsException;
import com.example.multiplace.service.exception.IdentificationNumberAlreadyExistsException;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static com.example.multiplace.model.enums.UserRoleEnum.COMPANY;
import static com.example.multiplace.model.enums.UserTypeEntity.BULSTAT;

@Service
public class UserEntityService {
    private final UserDetailsService userDetailsService;
    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;


    public UserEntityService(UserDetailsService userDetailsService, UserRoleRepository userRoleRepository,
                             UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userDetailsService = userDetailsService;
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    public void registerUser(UserRegistrationDTO registrationDTO,
                             Consumer<Authentication> successfulLoginProcessor) {
        if (userRepository.existsByEmail(registrationDTO.getEmail())) {
            throw new EmailAlreadyExistsException("Email is already taken");
        }

        // Проверка за вече съществуващ идентификационен номер
        if (userRepository.existsByIdentificationNumber(registrationDTO.getIdentificationNumber())) {
            throw new IdentificationNumberAlreadyExistsException("Identification number is already taken");
        }


        UserEntity userEntity = new UserEntity().
                setUsername(registrationDTO.getUsername()).
                setUserTypeEntity(registrationDTO.getUserTypeEntity()).
                setIdentificationNumber(registrationDTO.getIdentificationNumber()).
                setEmail(registrationDTO.getEmail()).
                setPassword(passwordEncoder.encode(registrationDTO.getPassword()));


        if (registrationDTO.getUserTypeEntity().equals(BULSTAT)) {
            userEntity.
                    setRoles(Collections.singletonList(userRoleRepository.
                            findUserRoleEntityByRole(COMPANY).orElseThrow()));

        }
        userRepository.save(userEntity);

        UserDetails userDetails = userDetailsService.loadUserByUsername(registrationDTO.getEmail());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );

        successfulLoginProcessor.accept(authentication);
    }

    public List<UserDTO> getAllUsers() {
        return
                userRepository.findAll().stream()
                        .map(UserEntityService::mapUserDTO)
                        .collect(Collectors.toList());

    }


    private static UserDTO mapUserDTO(UserEntity userEntity) {
        UserRoleEntityDTO userRoleEntityDTO = new UserRoleEntityDTO()
                .setRole(userEntity.getRoles().stream()
                        .map(UserRoleEntity::getRole)
                        .map(Enum::name)
                        .collect(Collectors.joining(", ")));

        return new UserDTO()
                .setId(userEntity.getId())
                .setEmail(userEntity.getEmail())
                .setUsername(userEntity.getUsername())
                .setIdentificationNumber(userEntity.getIdentificationNumber())
                .setRole(userRoleEntityDTO);
    }

    public Optional<UserDTO> findUserById(Long id) {
        return userRepository.findById(id)
                .map(UserEntityService::mapUserDTO);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }


    public void updateUserProfile(String email, String newPassword, String newIdentificationNumber) {
        userRepository.findByEmail(email).ifPresent(userEntity -> {

            if (newPassword != null && !newPassword.isEmpty()) {
                userEntity.setPassword(passwordEncoder.encode(newPassword));
            }


            if (newIdentificationNumber != null && !newIdentificationNumber.isEmpty()) {
                userEntity.setIdentificationNumber(newIdentificationNumber);
            }

            userRepository.save(userEntity);
        });
    }
}



