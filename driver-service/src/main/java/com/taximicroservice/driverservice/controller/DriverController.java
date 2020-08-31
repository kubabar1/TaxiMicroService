package com.taximicroservice.driverservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.taximicroservice.driverservice.exception.DriverServiceException;
import com.taximicroservice.driverservice.exception.ExternalServiceException;
import com.taximicroservice.driverservice.model.DriverAddDTO;
import com.taximicroservice.driverservice.model.DriverResponseDTO;
import com.taximicroservice.driverservice.model.utils.RestPageImpl;
import com.taximicroservice.driverservice.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;


    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @HystrixCommand(fallbackMethod = "getDriversPageFallback")
    public ResponseEntity<Page<DriverResponseDTO>> getDriversPage(@RequestParam(value = "page") int page,
                                                                  @RequestParam(value = "count") int count) {
        RestPageImpl<DriverResponseDTO> passengerResponseDTOPage;

        try {
            passengerResponseDTOPage = driverService.getDriversPage(page, count);
        } catch (DriverServiceException e) {
            return ResponseEntity.unprocessableEntity().build();
        } catch (ExternalServiceException e) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }

        if (passengerResponseDTOPage.getSize() == 0) {
            return new ResponseEntity<>(passengerResponseDTOPage, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(passengerResponseDTOPage, HttpStatus.OK);
        }
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @HystrixCommand(fallbackMethod = "addDriverFallback")
    public ResponseEntity<DriverResponseDTO> addDriver(@Valid @RequestBody DriverAddDTO passengerAddDTO) {
        try {
            return new ResponseEntity<>(driverService.addDriver(passengerAddDTO), HttpStatus.OK);
        } catch (ExternalServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<Page<DriverResponseDTO>> getDriversPageFallback(int page, int count, Throwable e) {
        return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }

    public ResponseEntity<DriverResponseDTO> addDriverFallback(DriverAddDTO passengerAddDTO, Throwable e) {
        return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }
    
}
