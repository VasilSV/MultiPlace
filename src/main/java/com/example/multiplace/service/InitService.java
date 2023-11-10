package com.example.multiplace.service;

import com.example.multiplace.model.entity.ToolEntity;
import com.example.multiplace.repository.ToolEntityRepository;
import com.example.multiplace.model.entity.UserEntity;
import com.example.multiplace.model.entity.UserRoleEntity;
import com.example.multiplace.model.enums.UserTypeEntity;
import com.example.multiplace.repository.UserRepository;
import com.example.multiplace.repository.UserRoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.List;

import static com.example.multiplace.model.enums.UserRoleEnum.*;

@Service
public class InitService {

    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;

    private PasswordEncoder passwordEncoder;
    private final ToolEntityRepository toolEntityRepository;

    public InitService(UserRepository userRepository,
                       UserRoleRepository userRoleRepository,
                       PasswordEncoder passwordEncoder,
                       @Value("${app.default.password}") String defaultPassword,
                       ToolEntityRepository toolEntityRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;

        this.toolEntityRepository = toolEntityRepository;
    }

    @PostConstruct
    public void init() {
        initRoles();
        initUsers();
        initTools();


    }

    private void initRoles() {
        if (userRoleRepository.count() == 0) {
            var adminRole = new UserRoleEntity().setRole(ADMIN);
            var companyRole = new UserRoleEntity().setRole(COMPANY);

            userRoleRepository.save(adminRole);
            userRoleRepository.save(companyRole);

        }
    }
    private void initTools(){
        if (toolEntityRepository.count()==0){
            initTool();
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
                .setUserTypeEntity(UserTypeEntity.BULSTAT)
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
                .setUserTypeEntity(UserTypeEntity.BULSTAT)
                .setIdentificationNumber("BG12345")
                .setPassword(passwordEncoder.encode("qqq"))
                .setRoles(List.of(companyRole));
        userRepository.save(companyUser);

    }

    private void initNormalUser() {

        UserEntity normalUser = new UserEntity()
                .setUsername("User")
                .setEmail("user@abv.bg")
                .setUserTypeEntity(UserTypeEntity.EGN)
                .setIdentificationNumber("7805214420")
                .setPassword(passwordEncoder.encode("qqq"));

        userRepository.save(normalUser);


    }

    private void initTool(){
        ToolEntity toolEntity= new ToolEntity()
                .setToolName("COSER")
                .setDescription("fowjfwoew")
                .setPrice(BigDecimal.ONE)
                ;
        toolEntityRepository.save(toolEntity);


    }
}
