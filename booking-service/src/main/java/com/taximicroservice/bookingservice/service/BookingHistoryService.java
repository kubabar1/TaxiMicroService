package com.taximicroservice.bookingservice.service;

import com.taximicroservice.bookingservice.exception.BookingServiceException;
import com.taximicroservice.bookingservice.model.dto.BookingResponseDTO;
import org.springframework.data.domain.Page;

public interface BookingHistoryService {

    Page<BookingResponseDTO> getDriverBookingsHistoryPage(Long driverId, int page, int count) throws BookingServiceException;

    Page<BookingResponseDTO> getPassengerBookingsHistoryPage(Long passengerId, int page, int count) throws BookingServiceException;

}
