package com.taximicroservice.bookingservice.service;

import com.taximicroservice.bookingservice.exception.BookingServiceException;
import com.taximicroservice.bookingservice.model.dto.BookingResponseDTO;
import org.springframework.data.domain.Page;

public interface BookingHistoryService {

    Page<BookingResponseDTO> getPreviousDriverBookingsPage(Long driverId, int page, int count) throws BookingServiceException;

    Page<BookingResponseDTO> getPreviousPassengerBookingsPage(Long passengerId, int page, int count) throws BookingServiceException;

}
