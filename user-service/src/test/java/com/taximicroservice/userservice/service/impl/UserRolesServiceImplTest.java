package com.taximicroservice.userservice.service.impl;

import com.taximicroservice.userservice.exception.UserServiceException;
import com.taximicroservice.userservice.model.dto.RoleDTO;
import com.taximicroservice.userservice.service.UserRolesService;
import com.taximicroservice.userservice.service.UserService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRolesServiceImplTest {

    @Autowired
    private UserRolesService userRolesService;

    private static RoleDTO passengerRole;

    private static RoleDTO driverRole;

    private static RoleDTO adminRole;

    private static RoleDTO accountantRole;


    @BeforeAll
    static void initBeforeAll() {
        passengerRole = new RoleDTO();
        passengerRole.setId(1L);
        passengerRole.setName("passenger");

        driverRole = new RoleDTO();
        driverRole.setId(2L);
        driverRole.setName("driver");

        adminRole = new RoleDTO();
        adminRole.setId(3L);
        adminRole.setName("admin");

        accountantRole = new RoleDTO();
        accountantRole.setId(4L);
        accountantRole.setName("accountant");
    }


    @Test
    void getUserRoles() {
        Set<RoleDTO> user1RolesSet = userRolesService.getUserRoles(1L);

        assertTrue(user1RolesSet.contains(passengerRole));
        assertTrue(user1RolesSet.contains(driverRole));
        assertTrue(user1RolesSet.contains(adminRole));
        assertTrue(user1RolesSet.contains(accountantRole));
        assertEquals(user1RolesSet.size(), 4);

        Set<RoleDTO> user2RolesSet = userRolesService.getUserRoles(2L);
        assertTrue(user2RolesSet.contains(passengerRole));
        assertEquals(user2RolesSet.size(), 1);

        assertThrows(EntityNotFoundException.class, () -> userRolesService.getUserRoles(123L));
    }

    @Test
    void addUserRole() {
        Set<RoleDTO> user3RolesSet1 = userRolesService.getUserRoles(3L);
        assertTrue(user3RolesSet1.contains(passengerRole));
        assertEquals(1, user3RolesSet1.size());

        userRolesService.addUserRole(3L, 2L);
        Set<RoleDTO> user3RolesSet2 = userRolesService.getUserRoles(3L);
        assertTrue(user3RolesSet2.contains(passengerRole));
        assertTrue(user3RolesSet2.contains(driverRole));
        assertEquals(2, user3RolesSet2.size());

        userRolesService.addUserRole(3L, 3L);
        Set<RoleDTO> user3RolesSet3 = userRolesService.getUserRoles(3L);
        assertTrue(user3RolesSet3.contains(passengerRole));
        assertTrue(user3RolesSet3.contains(driverRole));
        assertTrue(user3RolesSet3.contains(adminRole));

        userRolesService.addUserRole(3L, 4L);
        Set<RoleDTO> user3RolesSet4 = userRolesService.getUserRoles(3L);
        assertTrue(user3RolesSet4.contains(passengerRole));
        assertTrue(user3RolesSet4.contains(driverRole));
        assertTrue(user3RolesSet4.contains(adminRole));
        assertTrue(user3RolesSet4.contains(accountantRole));
        assertEquals(4, user3RolesSet4.size());

        assertThrows(EntityNotFoundException.class, () -> userRolesService.addUserRole(3L, 5L));
        assertThrows(EntityNotFoundException.class, () -> userRolesService.addUserRole(111L, 2L));
        assertThrows(EntityNotFoundException.class, () -> userRolesService.addUserRole(111L, 6L));

        assertThrows(UserServiceException.class, () -> userRolesService.addUserRole(1L, 1L));
    }

    @Test
    void deleteUserRole() {
        Set<RoleDTO> user5RolesSet1 = userRolesService.getUserRoles(5L);
        assertTrue(user5RolesSet1.contains(passengerRole));
        assertTrue(user5RolesSet1.contains(driverRole));
        assertTrue(user5RolesSet1.contains(adminRole));
        assertTrue(user5RolesSet1.contains(accountantRole));
        assertEquals(4, user5RolesSet1.size());

        userRolesService.deleteUserRole(5L, 1L);
        Set<RoleDTO> user5RolesSet2 = userRolesService.getUserRoles(5L);
        assertTrue(user5RolesSet2.contains(driverRole));
        assertTrue(user5RolesSet2.contains(adminRole));
        assertTrue(user5RolesSet2.contains(accountantRole));
        assertEquals(3, user5RolesSet2.size());

        userRolesService.deleteUserRole(5L, 2L);
        Set<RoleDTO> user5RolesSet3 = userRolesService.getUserRoles(5L);
        assertTrue(user5RolesSet3.contains(adminRole));
        assertTrue(user5RolesSet3.contains(accountantRole));
        assertEquals(2, user5RolesSet3.size());

        userRolesService.deleteUserRole(5L, 3L);
        Set<RoleDTO> user5RolesSet4 = userRolesService.getUserRoles(5L);
        assertTrue(user5RolesSet4.contains(accountantRole));
        assertEquals(1, user5RolesSet4.size());

        userRolesService.deleteUserRole(5L, 4L);
        Set<RoleDTO> user5RolesSet5 = userRolesService.getUserRoles(5L);
        assertEquals(0, user5RolesSet5.size());

        assertThrows(EntityNotFoundException.class, () -> userRolesService.deleteUserRole(3L, 5L));
        assertThrows(EntityNotFoundException.class, () -> userRolesService.deleteUserRole(111L, 2L));
        assertThrows(EntityNotFoundException.class, () -> userRolesService.deleteUserRole(111L, 6L));

        assertThrows(UserServiceException.class, () -> userRolesService.deleteUserRole(5L, 1L));
    }
}