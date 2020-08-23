package com.taximicroservice.passengerservice.service.impl;

import com.taximicroservice.passengerservice.exception.PassengerServiceException;
import com.taximicroservice.passengerservice.model.PassengerAddDTO;
import com.taximicroservice.passengerservice.model.PassengerResponseDTO;
import com.taximicroservice.passengerservice.service.PassengerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Value(value = "${passengerService.kafka.addPassengerTopic}")
    private String addPassengerTopic;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private KafkaTemplate<String, PassengerAddDTO> passengerAddDTOKafkaTemplate;


    @Override
    public Page<PassengerResponseDTO> getPassengersPage(int page, int count) throws PassengerServiceException {
        return null;
    }

    @Override
    public PassengerResponseDTO getPassengerById(Long passengerId) throws EntityNotFoundException {
        return null;
    }

    @Override
    public PassengerResponseDTO addPassenger(PassengerAddDTO passengerAddDTO) throws PassengerServiceException {
        passengerAddDTOKafkaTemplate.send(addPassengerTopic, new PassengerAddDTO());
        return modelMapper.map(passengerAddDTO, PassengerResponseDTO.class);
    }

}
