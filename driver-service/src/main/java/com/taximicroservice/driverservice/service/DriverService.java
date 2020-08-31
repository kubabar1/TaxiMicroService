package com.taximicroservice.driverservice.service;

import com.taximicroservice.driverservice.exception.DriverServiceException;
import com.taximicroservice.driverservice.exception.ExternalServiceException;
import com.taximicroservice.driverservice.model.DriverAddDTO;
import com.taximicroservice.driverservice.model.DriverResponseDTO;
import com.taximicroservice.driverservice.model.utils.RestPageImpl;

public interface DriverService {

    RestPageImpl<DriverResponseDTO> getDriversPage(int page, int count) throws DriverServiceException, ExternalServiceException;

    DriverResponseDTO addDriver(DriverAddDTO passengerAddDTO) throws ExternalServiceException;

}
