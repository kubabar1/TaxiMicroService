package com.taximicroservice.userservice.controller;

import com.taximicroservice.userservice.model.dto.RoleDTO;
import com.taximicroservice.userservice.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/roles")
public class RolesController {

    @Autowired
    private RolesService rolesService;

    @GetMapping(value = "/{roleId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable("roleId") Long roleId) {
        try {
            return new ResponseEntity<>(rolesService.getRoleById(roleId), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
