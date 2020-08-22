package com.taximicroservice.userservice.controller;

import com.taximicroservice.userservice.model.dto.RoleDTO;
import com.taximicroservice.userservice.service.RolesService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = RolesController.class)
class RolesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RolesService rolesServiceMock;

    private static RoleDTO roleDTO;

    @BeforeAll
    static void initBeforeAll() {
        roleDTO = new RoleDTO();
        roleDTO.setId(1L);
        roleDTO.setName("passenger");
    }

    @Test
    void getRoleById() throws Exception {
        when(rolesServiceMock.getRoleById(1L)).thenReturn(roleDTO);

        mockMvc.perform(get("/roles/{roleId}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("passenger"));
    }

}