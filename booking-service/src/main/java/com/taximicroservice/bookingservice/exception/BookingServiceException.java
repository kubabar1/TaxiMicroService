package com.taximicroservice.bookingservice.exception;

public class BookingServiceException extends RuntimeException {

    public BookingServiceException() {
        super();
    }

    public BookingServiceException(String message) {
        super(message);
    }

    public BookingServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookingServiceException(Throwable cause) {
        super(cause);
    }

}
