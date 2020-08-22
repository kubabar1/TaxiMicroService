package com.taximicroservice.userservice.service;

import com.taximicroservice.userservice.exception.UserServiceException;
import com.taximicroservice.userservice.model.dto.RoleDTO;

import javax.persistence.EntityNotFoundException;
import java.util.Set;

public interface UserRolesService {

    Set<RoleDTO> getUserRoles(Long userId) throws EntityNotFoundException;

    RoleDTO addUserRole(Long userId, Long roleId) throws EntityNotFoundException, UserServiceException;

    RoleDTO deleteUserRole(Long userId, Long roleId) throws EntityNotFoundException, UserServiceException;

}
