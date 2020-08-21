package com.taximicroservice.userservice.service.impl;

import com.taximicroservice.userservice.exception.UserServiceException;
import com.taximicroservice.userservice.model.dto.RoleDTO;
import com.taximicroservice.userservice.model.entity.RoleEntity;
import com.taximicroservice.userservice.model.entity.UserEntity;
import com.taximicroservice.userservice.repository.RoleRepository;
import com.taximicroservice.userservice.repository.UserRepository;
import com.taximicroservice.userservice.service.UserRolesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserRolesServiceImpl implements UserRolesService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Set<RoleDTO> getUserRoles(Long userId) throws EntityNotFoundException {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        return userEntity
                .getRoleEntitySet()
                .stream()
                .map(user -> modelMapper.map(user, RoleDTO.class))
                .collect(Collectors.toSet());
    }

    @Override
    public RoleDTO addUserRole(Long userId, Long roleId) throws EntityNotFoundException, UserServiceException {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        RoleEntity roleEntity = roleRepository.findById(roleId).orElseThrow(EntityNotFoundException::new);
        boolean roleAdded = userEntity.addRole(roleEntity);

        if (roleAdded) {
            userRepository.save(userEntity);
        } else {
            throw new UserServiceException("User is already assigned to this role");
        }

        return modelMapper.map(roleEntity, RoleDTO.class);
    }

    @Override
    public RoleDTO deleteUserRole(Long userId, Long roleId) throws EntityNotFoundException, UserServiceException {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        RoleEntity roleEntity = roleRepository.findById(roleId).orElseThrow(EntityNotFoundException::new);
        boolean roleRemoved = userEntity.removeRole(roleEntity);

        if (roleRemoved) {
            userRepository.save(userEntity);
        } else {
            throw new UserServiceException("User is not assigned to this role");
        }

        return modelMapper.map(roleEntity, RoleDTO.class);
    }

}
