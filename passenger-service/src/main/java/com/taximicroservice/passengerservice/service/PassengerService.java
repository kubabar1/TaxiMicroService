package com.taximicroservice.passengerservice.service;

import com.taximicroservice.passengerservice.exception.PassengerServiceException;
import com.taximicroservice.passengerservice.model.PassengerAddDTO;
import com.taximicroservice.passengerservice.model.PassengerResponseDTO;
import org.springframework.data.domain.Page;

import javax.persistence.EntityNotFoundException;

public interface PassengerService {

    Page<PassengerResponseDTO> getPassengersPage(int page, int count) throws PassengerServiceException;

    PassengerResponseDTO getPassengerById(Long passengerId) throws EntityNotFoundException;

    PassengerResponseDTO addPassenger(PassengerAddDTO passengerAddDTO) throws PassengerServiceException;

}
