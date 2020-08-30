package com.taximicroservice.userservice.service;

import com.taximicroservice.userservice.model.dto.UserAddDTO;
import com.taximicroservice.userservice.model.dto.UserResponseDTO;
import com.taximicroservice.userservice.model.dto.kafka.PageRequestDTO;
import com.taximicroservice.userservice.model.dto.kafka.UserResponseDTOPage;

public interface KafkaListenerService {

    UserResponseDTO addPassengerListener(UserAddDTO userAddDTO);

    UserResponseDTOPage getPassengersPageListener(PageRequestDTO pageRequestDTO);

}
