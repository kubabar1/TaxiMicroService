package com.taximicroservice.userservice.service.impl;

import com.taximicroservice.userservice.model.dto.RoleDTO;
import com.taximicroservice.userservice.repository.RoleRepository;
import com.taximicroservice.userservice.service.RolesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class RolesServiceImpl implements RolesService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public RoleDTO getRoleById(Long roleId) {
        return modelMapper.map(roleRepository.findById(roleId).orElseThrow(EntityNotFoundException::new), RoleDTO.class);
    }

}
