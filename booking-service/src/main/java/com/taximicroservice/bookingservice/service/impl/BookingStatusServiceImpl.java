package com.taximicroservice.bookingservice.service.impl;

import com.taximicroservice.bookingservice.model.dto.BookingResponseDTO;
import com.taximicroservice.bookingservice.service.BookingStatusService;
import org.springframework.stereotype.Service;

@Service
public class BookingStatusServiceImpl implements BookingStatusService {

    @Override
    public BookingResponseDTO assignDriverToBooking(Long bookingId) {
        return null;
    }

    @Override
    public BookingResponseDTO abortBooking(Long bookingId) {
        return null;
    }

    @Override
    public BookingResponseDTO cancelBooking(Long bookingId) {
        return null;
    }

    @Override
    public BookingResponseDTO finishBooking(Long bookingId) {
        return null;
    }

    @Override
    public BookingResponseDTO removeBooking(Long bookingId) {
        return null;
    }

    @Override
    public BookingResponseDTO startBooking(Long bookingId) {
        return null;
    }

}
