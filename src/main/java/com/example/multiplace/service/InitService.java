package com.example.multiplace.service;

import com.example.multiplace.model.entity.OrdersEntity;
import com.example.multiplace.model.entity.ToolEntity;
import com.example.multiplace.repository.OrdersRepository;
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
import java.time.LocalDateTime;
import java.util.List;

import static com.example.multiplace.model.enums.UserRoleEnum.*;

@Service
public class InitService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    private final PasswordEncoder passwordEncoder;
    private final ToolEntityRepository toolEntityRepository;
    private final OrdersRepository ordersRepository;

    public InitService(UserRepository userRepository,
                       UserRoleRepository userRoleRepository,
                       PasswordEncoder passwordEncoder,
                       @Value("${app.default.password}") String defaultPassword,
                       ToolEntityRepository toolEntityRepository,
                       OrdersRepository ordersRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;

        this.toolEntityRepository = toolEntityRepository;
        this.ordersRepository = ordersRepository;
    }

    @PostConstruct
    public void init() {
        initRoles();
        initUsers();
        initTools();
        initOrders();

    }

    private void initRoles() {
        if (userRoleRepository.count() == 0) {
            var adminRole = new UserRoleEntity().setRole(ADMIN);
            var companyRole = new UserRoleEntity().setRole(COMPANY);

            userRoleRepository.save(adminRole);
            userRoleRepository.save(companyRole);

        }
    }

    private void initTools() {
        if (toolEntityRepository.count() == 0) {
            initTool();
        }
    }

    private void initOrders() {
        if (ordersRepository.count() == 0) {
            initOrder();
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

    private void initOrder() {
        UserEntity customer = userRepository.findByEmail("aladin@abv.bg")
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        List<ToolEntity> orderedTools =
                toolEntityRepository.findByToolNameIn(List.of("Gloves", "Hammer"));


        OrdersEntity firstOrder = new OrdersEntity()
                .setOrderTime(LocalDateTime.now())
                .setOrderPrice(BigDecimal.TEN)
                .setQuantity(1)
                .setCustomer(customer)
                .setOrderedTools(orderedTools);

        OrdersEntity secOrder = new OrdersEntity()
                .setOrderTime(LocalDateTime.now())
                .setOrderPrice(BigDecimal.ONE)
                .setQuantity(2)
                .setCustomer(customer)
                .setOrderedTools(orderedTools);
        ordersRepository.saveAll(List.of(firstOrder, secOrder));
    }

    private void initTool() {
        ToolEntity toolCoser = new ToolEntity()
                .setToolName("Coser")
                .setDescription("Градински трион и ножица за високи клони – КОСЕР")
                .setPrice(BigDecimal.ONE);
        ToolEntity toolHammer = new ToolEntity()
                .setToolName("Hammer")
                .setDescription("Чук кози крак – извит, 200 гр. – ЧУК")
                .setPrice(BigDecimal.ONE);
        ToolEntity toolMagneticHammer = new ToolEntity()
                .setToolName("Magnetic Hammer")
                .setDescription("Чук магнитен, тип кози крак 570 гр. – ЧУК МАГНИТЕН")
                .setPrice(BigDecimal.ONE);
        ToolEntity toolHelmet = new ToolEntity()
                .setToolName("Helmet")
                .setDescription("Строителна каска синя, електроустойчива – КАСКА")
                .setPrice(BigDecimal.ONE);
        ToolEntity toolGloves = new ToolEntity()
                .setToolName("Gloves")
                .setDescription("Работни ръкавици – полиестерни, нитрилни - РЪКАВИЦИ")
                .setPrice(BigDecimal.ONE);
        ToolEntity toolShovel = new ToolEntity()
                .setToolName("Shovel")
                .setDescription("Дълга заоблена лопата със заострен връх – ЛОПАТА")
                .setPrice(BigDecimal.ONE);

        toolEntityRepository.saveAll(List.of(toolGloves, toolCoser, toolHammer, toolHelmet
                , toolMagneticHammer, toolShovel));


    }

    public void initRolesWrapper() {
        initRoles();
    }

    public void initToolsWrapper() {
        initTools();
    }

    public void initUsersWrapper() {
        initUsers();
    }

    public void initAdminWrapper() {
        initUsers();
    }

    public void initNormalWrapper() {
        initUsers();
    }

}
