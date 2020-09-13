package com.taximicroservice.userservice.service;

import com.taximicroservice.userservice.exception.UserServiceException;
import com.taximicroservice.userservice.model.dto.UserAddDTO;
import com.taximicroservice.userservice.model.dto.UserResponseDTO;
import com.taximicroservice.userservice.model.dto.kafka.PageRequestDTO;
import com.taximicroservice.userservice.model.dto.kafka.UserResponseDTOPage;

import javax.persistence.EntityNotFoundException;

public interface KafkaListenerService {

    UserResponseDTO addPassengerListener(UserAddDTO userAddDTO) throws EntityNotFoundException;

    UserResponseDTOPage getPassengersPageListener(PageRequestDTO pageRequestDTO) throws UserServiceException;

    UserResponseDTO addDriverListener(UserAddDTO userAddDTO) throws EntityNotFoundException;

    UserResponseDTOPage getDriversPageListener(PageRequestDTO pageRequestDTO) throws UserServiceException;

    UserResponseDTO getUserResponseDTOByIdListener(Long userId) throws UserServiceException;

}
