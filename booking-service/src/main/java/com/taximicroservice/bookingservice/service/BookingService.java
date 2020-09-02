package com.taximicroservice.bookingservice.service;

import com.taximicroservice.bookingservice.model.dto.BookingAddDTO;
import com.taximicroservice.bookingservice.model.dto.BookingResponseDTO;
import com.taximicroservice.bookingservice.model.dto.LocalisationDTO;
import org.springframework.data.domain.Page;

public interface BookingService {

    Page<BookingResponseDTO> getBookingsPage(int page, int count);

    BookingResponseDTO addBooking(BookingAddDTO bookingAddDTO);

    BookingResponseDTO getBookingById(Long bookingId);

    Page<BookingResponseDTO> getBookingsAssignedToDriver(Long bookingId, int page, int count);

    Page<BookingResponseDTO> getNearbyCreatedBookings(LocalisationDTO localisationDTO, int page, int count);

}
