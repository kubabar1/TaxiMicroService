package com.taximicroservice.userservice.service;

import com.taximicroservice.userservice.model.dto.UserAddDTO;
import com.taximicroservice.userservice.model.dto.UserResponseDTO;

public interface KafkaListenerService {

    UserResponseDTO addPassengerListener(UserAddDTO userAddDTO);

}
