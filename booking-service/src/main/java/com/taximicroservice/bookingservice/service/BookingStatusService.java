package com.taximicroservice.bookingservice.service;

import com.taximicroservice.bookingservice.model.dto.BookingResponseDTO;

import javax.persistence.EntityNotFoundException;

public interface BookingStatusService {

    BookingResponseDTO assignDriverToBooking(Long driverId, Long bookingId) throws EntityNotFoundException;

    BookingResponseDTO abortBooking(Long bookingId);

    BookingResponseDTO cancelBooking(Long bookingId);

    BookingResponseDTO finishBooking(Long bookingId);

    BookingResponseDTO removeBooking(Long bookingId);

    BookingResponseDTO startBookingProgress(Long bookingId);

}
