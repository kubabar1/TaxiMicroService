package com.taximicroservice.notificationservice.exception;

public class NotificationServiceException extends RuntimeException {

    public NotificationServiceException() {
        super();
    }

    public NotificationServiceException(String message) {
        super(message);
    }

    public NotificationServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotificationServiceException(Throwable cause) {
        super(cause);
    }

}
