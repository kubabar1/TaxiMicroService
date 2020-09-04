package com.taximicroservice.bookingservice.controller;

import com.taximicroservice.bookingservice.exception.BookingServiceException;
import com.taximicroservice.bookingservice.model.dto.BookingResponseDTO;
import com.taximicroservice.bookingservice.service.BookingHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings/history")
public class BookingHistoryController {

    @Autowired
    private BookingHistoryService bookingHistoryService;


    @GetMapping(value = "/driver/{driverId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Page<BookingResponseDTO>> getPreviousDriverBookingsPage(@PathVariable("driverId") Long bookingId,
                                                                                  @RequestParam(value = "page") int page,
                                                                                  @RequestParam(value = "count") int count) {
        Page<BookingResponseDTO> bookingResponseDTOPage;

        try {
            bookingResponseDTOPage = bookingHistoryService.getPreviousDriverBookingsPage(bookingId, page, count);
        } catch (BookingServiceException e) {
            return ResponseEntity.unprocessableEntity().build();
        }

        if (bookingResponseDTOPage.getSize() == 0) {
            return new ResponseEntity<>(bookingResponseDTOPage, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(bookingResponseDTOPage, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/passenger/{passengerId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Page<BookingResponseDTO>> getPreviousPassengerBookingsPage(@PathVariable("passengerId") Long bookingId,
                                                                                     @RequestParam(value = "page") int page,
                                                                                     @RequestParam(value = "count") int count) {
        Page<BookingResponseDTO> bookingResponseDTOPage;

        try {
            bookingResponseDTOPage = bookingHistoryService.getPreviousPassengerBookingsPage(bookingId, page, count);
        } catch (BookingServiceException e) {
            return ResponseEntity.unprocessableEntity().build();
        }

        if (bookingResponseDTOPage.getSize() == 0) {
            return new ResponseEntity<>(bookingResponseDTOPage, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(bookingResponseDTOPage, HttpStatus.OK);
        }
    }

}
