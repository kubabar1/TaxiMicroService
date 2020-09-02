package com.taximicroservice.bookingservice.service.impl;

import com.taximicroservice.bookingservice.model.dto.BookingAddDTO;
import com.taximicroservice.bookingservice.model.dto.BookingResponseDTO;
import com.taximicroservice.bookingservice.model.dto.LocalisationDTO;
import com.taximicroservice.bookingservice.service.BookingService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {

    @Override
    public Page<BookingResponseDTO> getBookingsPage(int page, int count) {
        return null;
    }

    @Override
    public BookingResponseDTO addBooking(BookingAddDTO bookingAddDTO) {
        return null;
    }

    @Override
    public BookingResponseDTO getBookingById(Long bookingId) {
        return null;
    }

    @Override
    public Page<BookingResponseDTO> getBookingsAssignedToDriver(Long bookingId, int page, int count) {
        return null;
    }

    @Override
    public Page<BookingResponseDTO> getNearbyCreatedBookings(LocalisationDTO localisationDTO, int page, int count) {
        return null;
    }

}
