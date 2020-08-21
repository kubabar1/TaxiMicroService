package com.taximicroservice.userservice.service.impl;

import com.taximicroservice.userservice.model.dto.RoleDTO;
import com.taximicroservice.userservice.service.RolesService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RolesServiceImplTest {

    @Autowired
    private RolesService rolesService;

    @Test
    void getRoleById() {
        RoleDTO role1 = rolesService.getRoleById(1L);
        RoleDTO role2 = rolesService.getRoleById(2L);
        RoleDTO role3 = rolesService.getRoleById(3L);
        RoleDTO role4 = rolesService.getRoleById(4L);

        assertEquals(role1.getId(), 1L);
        assertEquals(role1.getName(), "passenger");
        assertEquals(role2.getId(), 2L);
        assertEquals(role2.getName(), "driver");
        assertEquals(role3.getId(), 3L);
        assertEquals(role3.getName(), "admin");
        assertEquals(role4.getId(), 4L);
        assertEquals(role4.getName(), "accountant");

        assertThrows(EntityNotFoundException.class, () -> rolesService.getRoleById(5L));
    }

}