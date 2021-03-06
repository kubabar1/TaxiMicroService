package com.taximicroservice.bookingservice.service;

import com.taximicroservice.bookingservice.exception.BookingServiceException;
import com.taximicroservice.bookingservice.exception.ExternalServiceException;
import com.taximicroservice.bookingservice.model.dto.BookingAddDTO;
import com.taximicroservice.bookingservice.model.dto.BookingResponseDTO;
import com.taximicroservice.bookingservice.model.dto.LocalisationDTO;
import org.locationtech.jts.io.ParseException;
import org.springframework.data.domain.Page;

import javax.persistence.EntityNotFoundException;

public interface BookingService {

    Page<BookingResponseDTO> getBookingsPage(int page, int count) throws BookingServiceException;

    BookingResponseDTO addBooking(BookingAddDTO bookingAddDTO) throws EntityNotFoundException, BookingServiceException, ExternalServiceException;

    BookingResponseDTO getBookingById(Long bookingId) throws EntityNotFoundException;

    Page<BookingResponseDTO> getBookingsAssignedToDriver(Long driverId, int page, int count)
            throws BookingServiceException, EntityNotFoundException;

    Page<BookingResponseDTO> getNearbyCreatedBookings(LocalisationDTO localisationDTO, double distance, int page, int count)
            throws BookingServiceException, ParseException;

}
