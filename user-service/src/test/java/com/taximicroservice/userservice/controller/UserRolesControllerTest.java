package com.taximicroservice.userservice.controller;

import com.taximicroservice.userservice.exception.UserServiceException;
import com.taximicroservice.userservice.model.dto.RoleDTO;
import com.taximicroservice.userservice.service.UserRolesService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserRolesController.class)
class UserRolesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRolesService userRolesServiceMock;

    private static RoleDTO passengerRole;

    private static RoleDTO driverRole;

    private static RoleDTO adminRole;

    private static RoleDTO accountantRole;

    private static Set<RoleDTO> roleDTOSet;

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

        roleDTOSet = new HashSet<>();
        roleDTOSet.add(passengerRole);
        roleDTOSet.add(driverRole);
        roleDTOSet.add(adminRole);
        roleDTOSet.add(accountantRole);
    }


    @Test
    void getUserRoles() throws Exception {
        when(userRolesServiceMock.getUserRoles(1L)).thenReturn(roleDTOSet);
        when(userRolesServiceMock.getUserRoles(111L)).thenThrow(EntityNotFoundException.class);

        mockMvc.perform(get("/users/roles/{userId}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].name").value("passenger"))
                .andExpect(jsonPath("$.[1].id").value(4))
                .andExpect(jsonPath("$.[1].name").value("accountant"))
                .andExpect(jsonPath("$.[2].id").value(2))
                .andExpect(jsonPath("$.[2].name").value("driver"))
                .andExpect(jsonPath("$.[3].id").value(3))
                .andExpect(jsonPath("$.[3].name").value("admin"));

        mockMvc.perform(get("/users/roles/{userId}", 111L))
                .andExpect(status().isNotFound());
    }

    @Test
    void addUserRole() throws Exception {
        when(userRolesServiceMock.addUserRole(1L, 2L)).thenReturn(driverRole);
        when(userRolesServiceMock.addUserRole(111L, 2L)).thenThrow(EntityNotFoundException.class);
        when(userRolesServiceMock.addUserRole(1L, 111L)).thenThrow(EntityNotFoundException.class);
        when(userRolesServiceMock.addUserRole(111L, 111L)).thenThrow(EntityNotFoundException.class);
        when(userRolesServiceMock.addUserRole(1L, 1L)).thenThrow(UserServiceException.class);

        mockMvc.perform(post("/users/roles/{userId}", 1L)
                .param("roleId", "2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value("driver"));

        mockMvc.perform(post("/users/roles/{userId}", 111L)
                .param("roleId", "2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        mockMvc.perform(post("/users/roles/{userId}", 1L)
                .param("roleId", "111")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        mockMvc.perform(post("/users/roles/{userId}", 111L)
                .param("roleId", "111")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        mockMvc.perform(post("/users/roles/{userId}", 1L)
                .param("roleId", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void deleteUserRole() throws Exception {
        when(userRolesServiceMock.deleteUserRole(1L, 2L)).thenReturn(driverRole);
        when(userRolesServiceMock.deleteUserRole(111L, 2L)).thenThrow(EntityNotFoundException.class);
        when(userRolesServiceMock.deleteUserRole(1L, 111L)).thenThrow(EntityNotFoundException.class);
        when(userRolesServiceMock.deleteUserRole(111L, 111L)).thenThrow(EntityNotFoundException.class);
        when(userRolesServiceMock.deleteUserRole(1L, 1L)).thenThrow(UserServiceException.class);

        mockMvc.perform(delete("/users/roles/{userId}", 1L)
                .param("roleId", "2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value("driver"));

        mockMvc.perform(delete("/users/roles/{userId}", 111L)
                .param("roleId", "2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        mockMvc.perform(delete("/users/roles/{userId}", 1L)
                .param("roleId", "111")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        mockMvc.perform(delete("/users/roles/{userId}", 111L)
                .param("roleId", "111")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        mockMvc.perform(delete("/users/roles/{userId}", 1L)
                .param("roleId", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

}