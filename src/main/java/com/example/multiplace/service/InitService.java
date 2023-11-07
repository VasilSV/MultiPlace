package com.example.multiplace.service;

import com.example.multiplace.model.entity.UserEntity;
import com.example.multiplace.model.entity.UserRoleEntity;
import com.example.multiplace.repository.UserRepository;
import com.example.multiplace.repository.UserRoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;

import static com.example.multiplace.model.enums.UserRoleEnum.*;

@Service
public class InitService {

    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;

    private PasswordEncoder passwordEncoder;

    public InitService(UserRepository userRepository,
                       UserRoleRepository userRoleRepository,
                       PasswordEncoder passwordEncoder,
                       @Value("${app.default.password}") String defaultPassword) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;

    }

    @PostConstruct
    public void init() {
        initRoles();
        initUsers();


    }

    private void initRoles() {
        if (userRoleRepository.count() == 0) {
            var adminRole = new UserRoleEntity().setRole(ADMIN);
            var companyRole = new UserRoleEntity().setRole(COMPANY);

            userRoleRepository.save(adminRole);
            userRoleRepository.save(companyRole);

        }
    }

    private void initUsers() {
        if (userRepository.count() == 0) {
            initAdmin();
            initCompany();
            initNormalUser();
        }
    }

    private void initAdmin() {

//            UserRoleEntity adminRole = userRoleRepository.findUserRoleEntityByRole(ADMIN)
//                    .orElseThrow(() -> new IllegalStateException(" greshka v rolqta admin"));
        UserEntity adminUser = new UserEntity()
                .setUsername("Admin")
                .setEmail("aladin@abv.bg")
                .setIdentificationNumber("12345")
                .setPassword(passwordEncoder.encode("qqq"))
                .setRoles(this.userRoleRepository.findAll());
        userRepository.save(adminUser);
    }

    private void initCompany() {

        UserRoleEntity companyRole = userRoleRepository.findUserRoleEntityByRole(COMPANY)
                .orElseThrow(() -> new IllegalStateException(" greshka v rolqta company"));
        UserEntity companyUser = new UserEntity()
                .setUsername("Company")
                .setEmail("company@abv.bg")
                .setIdentificationNumber("BG12345")
                .setPassword(passwordEncoder.encode("qqq"))
                .setRoles(List.of(companyRole));
        userRepository.save(companyUser);

    }

    private void initNormalUser() {

        UserEntity normalUser = new UserEntity()
                .setUsername("User")
                .setEmail("user@abv.bg")
                .setIdentificationNumber("7805214420")
                .setPassword(passwordEncoder.encode("qqq"));

        userRepository.save(normalUser);


    }

}
