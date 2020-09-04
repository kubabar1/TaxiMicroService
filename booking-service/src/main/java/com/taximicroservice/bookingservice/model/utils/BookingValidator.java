package com.taximicroservice.bookingservice.model.utils;

import com.taximicroservice.bookingservice.exception.BookingServiceException;

public class BookingValidator {

    public static void validatePageAndCount(int page, int count) throws BookingServiceException {
        if (page < 0) {
            throw new BookingServiceException("Page must be greater than or equal 0");
        }
        if (count <= 0) {
            throw new BookingServiceException("Count must be greater than 0");
        }
    }

}
