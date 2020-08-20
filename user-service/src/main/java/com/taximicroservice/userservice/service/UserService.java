package com.taximicroservice.userservice.service;

import com.taximicroservice.userservice.model.dto.UserResponseDTO;
import com.taximicroservice.userservice.model.dto.UserUpdateDTO;
import org.springframework.data.domain.Page;

import javax.persistence.EntityNotFoundException;


public interface UserService {

    Page<UserResponseDTO> getUsersPage(int page, int count);

    UserResponseDTO getUserWithId(Long userId) throws EntityNotFoundException;

    UserResponseDTO deleteUserWithId(Long userId) throws EntityNotFoundException;

    UserUpdateDTO updateUserWithId(Long userId, UserUpdateDTO userUpdateDTO) throws EntityNotFoundException;

}
