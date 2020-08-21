package com.taximicroservice.userservice.service.impl;

import com.taximicroservice.userservice.exception.UserServiceException;
import com.taximicroservice.userservice.model.dto.UserResponseDTO;
import com.taximicroservice.userservice.model.dto.UserUpdateDTO;
import com.taximicroservice.userservice.model.entity.RoleEntity;
import com.taximicroservice.userservice.model.entity.UserEntity;
import com.taximicroservice.userservice.repository.RoleRepository;
import com.taximicroservice.userservice.repository.UserRepository;
import com.taximicroservice.userservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Page<UserResponseDTO> getUsersPage(int page, int count) throws UserServiceException {
        if (page < 0) {
            throw new UserServiceException("Page must be greater than or equal 0");
        }
        if (count <= 0) {
            throw new UserServiceException("Count must be greater than 0");
        }

        return userRepository.findAll(PageRequest.of(page, count))
                .map(userEntity -> modelMapper.map(userEntity, UserResponseDTO.class));
    }

    @Override
    public UserResponseDTO getUserWithId(Long userId) throws EntityNotFoundException {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(userEntity, UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO deleteUserWithId(Long userId) throws EntityNotFoundException {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        userEntity.clearRolesSet();
        userRepository.delete(userEntity);
        return modelMapper.map(userEntity, UserResponseDTO.class);
    }

    @Override
    public UserUpdateDTO updateUserWithId(Long userId, UserUpdateDTO userUpdateDTO) throws EntityNotFoundException {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        userEntity.setName(userUpdateDTO.getName());
        userEntity.setSurname(userUpdateDTO.getSurname());
        userEntity.setPesel(userUpdateDTO.getPesel());
        userEntity.setBirthDate(userUpdateDTO.getBirthDate());
        userEntity.setPassword(userUpdateDTO.getPassword());
        userRepository.save(userEntity);
        return userUpdateDTO;
    }

}
