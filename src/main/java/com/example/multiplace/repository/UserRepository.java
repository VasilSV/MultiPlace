package com.example.multiplace.repository;

import com.example.multiplace.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);


    Optional<UserEntity> findByUsername(String username);


    boolean existsByEmail(String email);

    boolean existsByIdentificationNumber(String identificationNumber);
}

