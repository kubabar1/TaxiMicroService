package com.taximicroservice.userservice.repository;

import com.taximicroservice.userservice.model.entity.RoleEntity;
import com.taximicroservice.userservice.model.entity.UserEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private static RoleEntity passengerRole;

    private static RoleEntity driverRole;

    private static RoleEntity adminRole;

    private static RoleEntity accountantRole;


    @BeforeAll
    static void initBeforeAll() {
        passengerRole = new RoleEntity();
        passengerRole.setId(1L);
        passengerRole.setName("passenger");

        driverRole = new RoleEntity();
        driverRole.setId(2L);
        driverRole.setName("driver");

        adminRole = new RoleEntity();
        adminRole.setId(3L);
        adminRole.setName("admin");

        accountantRole = new RoleEntity();
        accountantRole.setId(4L);
        accountantRole.setName("accountant");
    }

    @Test
    void findByRoleEntitySet_id() {
        Page<UserEntity> passengerEntityPage = userRepository.findByRoleEntitySet_id(1L, PageRequest.of(0, 100));
        Page<UserEntity> driverEntityPage = userRepository.findByRoleEntitySet_id(2L, PageRequest.of(0, 100));
        Page<UserEntity> adminEntityPage = userRepository.findByRoleEntitySet_id(2L, PageRequest.of(0, 100));
        Page<UserEntity> accountantEntityPage = userRepository.findByRoleEntitySet_id(2L, PageRequest.of(0, 100));

        passengerEntityPage.getContent().forEach(passenger -> assertTrue(passenger.getRoleEntitySet().contains(passengerRole)));
        driverEntityPage.getContent().forEach(passenger -> assertTrue(passenger.getRoleEntitySet().contains(driverRole)));
        adminEntityPage.getContent().forEach(passenger -> assertTrue(passenger.getRoleEntitySet().contains(adminRole)));
        accountantEntityPage.getContent().forEach(passenger -> assertTrue(passenger.getRoleEntitySet().contains(accountantRole)));
    }

}