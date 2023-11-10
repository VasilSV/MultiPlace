package com.example.multiplace.model.entity;

import com.example.multiplace.model.enums.UserTypeEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserTypeEntity userTypeEntity;
    @Column(nullable = false, unique = true)
    private String identificationNumber;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<UserRoleEntity> roles = new ArrayList<>();

    @OneToMany(mappedBy = "username")
    private List<ToolEntity> toolsList;
    @OneToMany(mappedBy = "customer")
    private List<OrdersEntity> ordersEntityList;

    public UserEntity() {
    }

    public UserTypeEntity getUserTypeEntity() {
        return userTypeEntity;
    }

    public UserEntity setUserTypeEntity(UserTypeEntity userTypeEntity) {
        this.userTypeEntity = userTypeEntity;
        return this;
    }

    public List<OrdersEntity> getOrdersEntityList() {
        return ordersEntityList;
    }

    public UserEntity setOrdersEntityList(List<OrdersEntity> ordersEntityList) {
        this.ordersEntityList = ordersEntityList;
        return this;
    }

    public List<ToolEntity> getToolsList() {
        return toolsList;
    }

    public UserEntity setToolsList(List<ToolEntity> toolsList) {
        this.toolsList = toolsList;
        return this;
    }

    public Long getId() {
        return id;
    }

    public UserEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public UserEntity setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public List<UserRoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(List<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    public UserEntity addRole(UserRoleEntity role) {
        this.roles.add(role);
        return this;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", identificationNumber='" + identificationNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + (password != null ? "[PROVIDED]" : "[N/A]") + '\'' +
                ", roles=" + roles +
                ", toolsList=" + toolsList +
                '}';
    }

}
