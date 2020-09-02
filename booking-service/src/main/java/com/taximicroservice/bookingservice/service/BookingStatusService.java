package com.taximicroservice.bookingservice.service;

import com.taximicroservice.bookingservice.model.dto.BookingResponseDTO;

public interface BookingStatusService {

    BookingResponseDTO assignDriverToBooking(Long bookingId);

    BookingResponseDTO abortBooking(Long bookingId);

    BookingResponseDTO cancelBooking(Long bookingId);

    BookingResponseDTO finishBooking(Long bookingId);

    BookingResponseDTO removeBooking(Long bookingId);

    BookingResponseDTO startBooking(Long bookingId);

}
