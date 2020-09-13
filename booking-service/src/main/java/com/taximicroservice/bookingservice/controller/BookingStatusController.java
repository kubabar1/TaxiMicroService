package com.taximicroservice.bookingservice.controller;

import com.taximicroservice.bookingservice.exception.BookingServiceException;
import com.taximicroservice.bookingservice.model.dto.BookingResponseDTO;
import com.taximicroservice.bookingservice.service.BookingStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/bookings/status")
public class BookingStatusController {

    @Autowired
    private BookingStatusService bookingStatusService;


    @PutMapping(value = "/assign-driver/{bookingId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BookingResponseDTO> assignDriverToBooking(@PathVariable("bookingId") Long bookingId
            , @RequestParam("driverId") Long driverId) {
        try {
            return new ResponseEntity<>(bookingStatusService.assignDriverToBooking(driverId, bookingId), HttpStatus.OK);
        } catch (BookingServiceException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/unassign-driver/{bookingId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BookingResponseDTO> unassignDriverFromBooking(@PathVariable("bookingId") Long bookingId) {
        try {
            return new ResponseEntity<>(bookingStatusService.unassignDriverFromBooking(bookingId), HttpStatus.OK);
        } catch (BookingServiceException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/abort/{bookingId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BookingResponseDTO> abortBooking(@PathVariable("bookingId") Long bookingId) {
        try {
            return new ResponseEntity<>(bookingStatusService.abortBooking(bookingId), HttpStatus.OK);
        } catch (BookingServiceException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/cancel/{bookingId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BookingResponseDTO> cancelBooking(@PathVariable("bookingId") Long bookingId) {
        try {
            return new ResponseEntity<>(bookingStatusService.cancelBooking(bookingId), HttpStatus.OK);
        } catch (BookingServiceException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/finish/{bookingId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BookingResponseDTO> finishBooking(@PathVariable("bookingId") Long bookingId) {
        try {
            return new ResponseEntity<>(bookingStatusService.finishBooking(bookingId), HttpStatus.OK);
        } catch (BookingServiceException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/start/{bookingId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BookingResponseDTO> startBooking(@PathVariable("bookingId") Long bookingId) {
        try {
            return new ResponseEntity<>(bookingStatusService.startBookingProgress(bookingId), HttpStatus.OK);
        } catch (BookingServiceException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
