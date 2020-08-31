package com.taximicroservice.driverservice.exception;

public class DriverServiceException extends RuntimeException {

    public DriverServiceException() {
        super();
    }

    public DriverServiceException(String message) {
        super(message);
    }

    public DriverServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public DriverServiceException(Throwable cause) {
        super(cause);
    }

}
