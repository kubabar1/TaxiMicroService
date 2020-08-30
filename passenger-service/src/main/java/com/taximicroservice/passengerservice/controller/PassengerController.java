package com.taximicroservice.passengerservice.controller;

import com.taximicroservice.passengerservice.exception.PassengerServiceException;
import com.taximicroservice.passengerservice.model.PassengerAddDTO;
import com.taximicroservice.passengerservice.model.PassengerResponseDTO;
import com.taximicroservice.passengerservice.model.utils.RestPageImpl;
import com.taximicroservice.passengerservice.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/passengers")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;


    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Page<PassengerResponseDTO>> getPassengersPage(@RequestParam(value = "page") int page,
                                                                        @RequestParam(value = "count") int count) {
        RestPageImpl<PassengerResponseDTO> passengerResponseDTOPage;

        try {
            passengerResponseDTOPage = passengerService.getPassengersPage(page, count);
        } catch (PassengerServiceException e) {
            return ResponseEntity.unprocessableEntity().build();
        }

        if (passengerResponseDTOPage.getSize() == 0) {
            return new ResponseEntity<>(passengerResponseDTOPage, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(passengerResponseDTOPage, HttpStatus.OK);
        }
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PassengerResponseDTO> addPassenger(@Valid @RequestBody PassengerAddDTO passengerAddDTO) {
        try {
            return new ResponseEntity<>(passengerService.addPassenger(passengerAddDTO), HttpStatus.OK);
        } catch (PassengerServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
