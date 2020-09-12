package com.taximicroservice.bookingservice.service;

import com.taximicroservice.bookingservice.exception.BookingServiceException;
import com.taximicroservice.bookingservice.model.dto.BookingResponseDTO;

import javax.persistence.EntityNotFoundException;

public interface BookingStatusService {

    BookingResponseDTO assignDriverToBooking(Long driverId, Long bookingId) throws BookingServiceException, EntityNotFoundException;

    BookingResponseDTO unassignDriverFromBooking(Long bookingId) throws BookingServiceException, EntityNotFoundException;

    BookingResponseDTO abortBooking(Long bookingId) throws BookingServiceException, EntityNotFoundException;

    BookingResponseDTO cancelBooking(Long bookingId) throws BookingServiceException, EntityNotFoundException;

    BookingResponseDTO finishBooking(Long bookingId) throws BookingServiceException, EntityNotFoundException;

    BookingResponseDTO startBookingProgress(Long bookingId) throws BookingServiceException, EntityNotFoundException;

}
