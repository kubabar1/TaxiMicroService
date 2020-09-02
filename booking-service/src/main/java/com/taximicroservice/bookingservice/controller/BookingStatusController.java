package com.taximicroservice.bookingservice.controller;

import com.taximicroservice.bookingservice.model.dto.BookingResponseDTO;
import com.taximicroservice.bookingservice.service.BookingStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookings/status")
public class BookingStatusController {

    @Autowired
    private BookingStatusService bookingStatusService;


    @PutMapping(value = "/assign-driver/{bookingId}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BookingResponseDTO> assignDriverToBooking(@PathVariable("bookingId") Long bookingId) {
        bookingStatusService.assignDriverToBooking(bookingId);
        return null;
    }

    @PutMapping(value = "/abort/{bookingId}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BookingResponseDTO> abortBooking(@PathVariable("bookingId") Long bookingId) {
        bookingStatusService.abortBooking(bookingId);
        return null;
    }

    @PutMapping(value = "/cancel/{bookingId}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BookingResponseDTO> cancelBooking(@PathVariable("bookingId") Long bookingId) {
        bookingStatusService.cancelBooking(bookingId);
        return null;
    }

    @PutMapping(value = "/finish/{bookingId}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BookingResponseDTO> finishBooking(@PathVariable("bookingId") Long bookingId) {
        bookingStatusService.finishBooking(bookingId);
        return null;
    }

    @PutMapping(value = "/remove/{bookingId}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BookingResponseDTO> removeBooking(@PathVariable("bookingId") Long bookingId) {
        bookingStatusService.removeBooking(bookingId);
        return null;
    }

    @PutMapping(value = "/start/{bookingId}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BookingResponseDTO> startBooking(@PathVariable("bookingId") Long bookingId) {
        bookingStatusService.startBooking(bookingId);
        return null;
    }

}
