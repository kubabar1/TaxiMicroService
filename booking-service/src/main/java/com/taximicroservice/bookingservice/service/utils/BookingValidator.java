package com.taximicroservice.bookingservice.service.utils;

import com.taximicroservice.bookingservice.exception.BookingServiceException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookingValidator {

    public void validatePageAndCount(int page, int count) throws BookingServiceException {
        if (page < 0) {
            throw new BookingServiceException("Page must be greater than or equal 0");
        }
        if (count <= 0) {
            throw new BookingServiceException("Count must be greater than 0");
        }
    }

    public void validatePassengerId(Long passengerId) throws BookingServiceException {
        throw new BookingServiceException("");
    }

    public void validateDriverId(Long driverId) throws BookingServiceException {
        throw new BookingServiceException("");
    }

    public void validateBookingCurrentStatus(List<String> expectedBookingStatusIdList, String currentBookingStatusId) throws BookingServiceException {
        if (!expectedBookingStatusIdList.contains(currentBookingStatusId)) {
            throw new BookingServiceException("Invalid booking status");
        }
    }

}
