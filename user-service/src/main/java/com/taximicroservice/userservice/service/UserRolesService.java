package com.taximicroservice.userservice.service;

import com.taximicroservice.userservice.model.dto.RoleDTO;

import javax.persistence.EntityNotFoundException;
import java.util.Set;

public interface UserRolesService {

    Set<RoleDTO> getUserRoles(Long userId) throws EntityNotFoundException;

    RoleDTO addUserRole(Long userId, Long roleId) throws EntityNotFoundException;

    RoleDTO deleteUserRole(Long userId, Long roleId) throws EntityNotFoundException;

}
