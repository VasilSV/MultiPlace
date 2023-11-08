package com.example.multiplace.service;

import com.example.multiplace.dtos.UserDTO;
import com.example.multiplace.dtos.UserRegistrationDTO;
import com.example.multiplace.dtos.UserRoleEntityDTO;
import com.example.multiplace.model.entity.UserEntity;
import com.example.multiplace.repository.UserRepository;
import com.example.multiplace.repository.UserRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

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

        UserEntity userEntity = new UserEntity().
                setUsername(registrationDTO.getUsername()).
                setIdentificationNumber(registrationDTO.getIdentificationNumber()).
                setEmail(registrationDTO.getEmail()).
                setPassword(passwordEncoder.encode(registrationDTO.getPassword()));

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
    private static UserDTO mapUserDTO (UserEntity userEntity){
        UserRoleEntityDTO userRoleEntityDTO = new UserRoleEntityDTO()
                .setRole(userEntity.getRoles().toString());
        return new UserDTO()
                .setId(userEntity.getId())
                .setEmail(userEntity.getEmail())
                .setUsername(userEntity.getUsername())
                .setIdentificationNumber(userEntity.getIdentificationNumber())
                .setRole(userRoleEntityDTO);
    }
}


//    public void registerUser(
//            UserRegistrationDTO userRegistrationDTO) {
//
//
//        userRepository.save(map(userRegistrationDTO));
//
//    }
//
//    private UserEntity map(UserRegistrationDTO userRegistrationDTO) {
//        return new UserEntity()
//             //   .setActive(true)
//                .setUsername(userRegistrationDTO.getUsername())
//                .setIdentificationNumber(userRegistrationDTO.getIdentificationNumber())
//                .setEmail(userRegistrationDTO.getEmail())
//                .setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
//    }
//}

