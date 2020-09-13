package com.taximicroservice.bookingservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.taximicroservice.bookingservice.exception.BookingServiceException;
import com.taximicroservice.bookingservice.model.dto.BookingAddDTO;
import com.taximicroservice.bookingservice.model.dto.BookingResponseDTO;
import com.taximicroservice.bookingservice.model.dto.LocalisationDTO;
import com.taximicroservice.bookingservice.service.BookingService;
import org.locationtech.jts.io.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;


    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Page<BookingResponseDTO>> getBookingsPage(@RequestParam(value = "page") int page,
                                                                    @RequestParam(value = "count") int count) {
        Page<BookingResponseDTO> passengerResponseDTOPage;

        try {
            passengerResponseDTOPage = bookingService.getBookingsPage(page, count);
        } catch (BookingServiceException e) {
            return ResponseEntity.unprocessableEntity().build();
        }

        if (passengerResponseDTOPage.getSize() == 0) {
            return new ResponseEntity<>(passengerResponseDTOPage, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(passengerResponseDTOPage, HttpStatus.OK);
        }
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @HystrixCommand(fallbackMethod = "addBookingFallback")
    public ResponseEntity<BookingResponseDTO> addBooking(@Valid @RequestBody BookingAddDTO bookingAddDTO) {
        try {
            return new ResponseEntity<>(bookingService.addBooking(bookingAddDTO), HttpStatus.OK);
        } catch (BookingServiceException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{bookingId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BookingResponseDTO> getBookingById(@PathVariable("bookingId") Long bookingId) {
        try {
            return new ResponseEntity<>(bookingService.getBookingById(bookingId), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/assigned/{driverId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Page<BookingResponseDTO>> getBookingsAssignedToDriver(@PathVariable("driverId") Long driverId,
                                                                                @RequestParam(value = "page") int page,
                                                                                @RequestParam(value = "count") int count) {
        Page<BookingResponseDTO> bookingResponseDTOPage;

        try {
            bookingResponseDTOPage = bookingService.getBookingsAssignedToDriver(driverId, page, count);
        } catch (BookingServiceException e) {
            return ResponseEntity.unprocessableEntity().build();
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (bookingResponseDTOPage.getSize() == 0) {
            return new ResponseEntity<>(bookingResponseDTOPage, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(bookingResponseDTOPage, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/nearby-created",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Page<BookingResponseDTO>> getNearbyCreatedBookings(
            @Valid @RequestBody LocalisationDTO driverLocalisation,
            @RequestParam(value = "distance") double distance,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "count") int count) {
        Page<BookingResponseDTO> bookingResponseDTOPage;

        try {
            bookingResponseDTOPage = bookingService.getNearbyCreatedBookings(driverLocalisation, distance, page, count);
        } catch (BookingServiceException | ParseException e) {
            e.printStackTrace();
            return ResponseEntity.unprocessableEntity().build();
        }

        if (bookingResponseDTOPage.getSize() == 0) {
            return new ResponseEntity<>(bookingResponseDTOPage, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(bookingResponseDTOPage, HttpStatus.OK);
        }
    }

    public ResponseEntity<BookingResponseDTO> addBookingFallback(BookingAddDTO bookingAddDTO) {
        return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }

}
