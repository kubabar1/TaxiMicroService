package com.taximicroservice.userservice.service;

import com.taximicroservice.userservice.model.dto.RoleDTO;

public interface RolesService {

    RoleDTO getRoleById(Long roleId);

}
