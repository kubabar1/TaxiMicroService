package com.taximicroservice.passengerservice.exception;

public class PassengerServiceException extends RuntimeException {

    public PassengerServiceException() {
        super();
    }

    public PassengerServiceException(String message) {
        super(message);
    }

    public PassengerServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public PassengerServiceException(Throwable cause) {
        super(cause);
    }

}
