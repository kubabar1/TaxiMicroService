package com.taximicroservice.bookingservice.controller;

import com.taximicroservice.bookingservice.exception.BookingServiceException;
import com.taximicroservice.bookingservice.exception.ExternalServiceException;
import com.taximicroservice.bookingservice.model.dto.BookingAddDTO;
import com.taximicroservice.bookingservice.model.dto.BookingResponseDTO;
import com.taximicroservice.bookingservice.model.dto.LocalisationDTO;
import com.taximicroservice.bookingservice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        } catch (ExternalServiceException e) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }

        if (passengerResponseDTOPage.getSize() == 0) {
            return new ResponseEntity<>(passengerResponseDTOPage, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(passengerResponseDTOPage, HttpStatus.OK);
        }
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BookingResponseDTO> addBooking(@Valid @RequestBody BookingAddDTO bookingAddDTO) {
        bookingService.addBooking(bookingAddDTO);
        return null;
    }

    @GetMapping(value = "/{bookingId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BookingResponseDTO> getBookingById(@PathVariable("bookingId") Long bookingId) {
        bookingService.getBookingById(bookingId);
        return null;
    }

    @GetMapping(value = "/assigned/{bookingId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Page<BookingResponseDTO>> getBookingsAssignedToDriver(@PathVariable("bookingId") Long bookingId,
                                                                                @RequestParam(value = "page") int page,
                                                                                @RequestParam(value = "count") int count) {
        bookingService.getBookingsAssignedToDriver(bookingId, page, count);
        return null;
    }

    @GetMapping(value = "/nearby-created",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Page<BookingResponseDTO>> getNearbyCreatedBookings(@Valid @RequestBody LocalisationDTO localisationDTO,
                                                                             @RequestParam(value = "page") int page,
                                                                             @RequestParam(value = "count") int count) {
        bookingService.getNearbyCreatedBookings(localisationDTO, page, count);
        return null;
    }

}
