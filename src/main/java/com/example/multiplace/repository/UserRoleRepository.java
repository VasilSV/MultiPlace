package com.example.multiplace.repository;

import com.example.multiplace.model.entity.UserRoleEntity;
import com.example.multiplace.model.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
    Optional<UserRoleEntity> findUserRoleEntityByRole(UserRoleEnum role);
    List<UserRoleEntity>findAllByRoleIn(List<UserRoleEnum> roles);
}
