package com.taximicroservice.userservice.controller;

import com.taximicroservice.userservice.exception.UserServiceException;
import com.taximicroservice.userservice.model.dto.RoleDTO;
import com.taximicroservice.userservice.service.UserRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Set;

@RestController
@RequestMapping("/users/roles")
public class UserRolesController {

    @Autowired
    private UserRolesService userRolesService;


    @GetMapping(value = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Set<RoleDTO>> getUserRoles(@PathVariable(value = "userId") Long userId) {
        try {
            return new ResponseEntity<>(userRolesService.getUserRoles(userId), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/{userId}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<RoleDTO> addUserRole(@PathVariable(value = "userId") Long userId,
                                               @RequestParam(value = "roleId") Long roleId) {
        try {
            return new ResponseEntity<>(userRolesService.addUserRole(userId, roleId), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (UserServiceException e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @DeleteMapping(value = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<RoleDTO> deleteUserRole(@PathVariable(value = "userId") Long userId,
                                                  @RequestParam(value = "roleId") Long roleId) {
        try {
            return new ResponseEntity<>(userRolesService.deleteUserRole(userId, roleId), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (UserServiceException e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

}
