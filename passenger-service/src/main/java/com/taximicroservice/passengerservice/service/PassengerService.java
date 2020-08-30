package com.taximicroservice.passengerservice.service;

import com.taximicroservice.passengerservice.exception.ExternalServiceException;
import com.taximicroservice.passengerservice.exception.PassengerServiceException;
import com.taximicroservice.passengerservice.model.PassengerAddDTO;
import com.taximicroservice.passengerservice.model.PassengerResponseDTO;
import com.taximicroservice.passengerservice.model.utils.RestPageImpl;

public interface PassengerService {

    RestPageImpl<PassengerResponseDTO> getPassengersPage(int page, int count) throws PassengerServiceException, ExternalServiceException;

    PassengerResponseDTO addPassenger(PassengerAddDTO passengerAddDTO) throws PassengerServiceException;

}
