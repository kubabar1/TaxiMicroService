package com.taximicroservice.chatservice.exception;

public class ChatServiceException extends RuntimeException {

    public ChatServiceException() {
        super();
    }

    public ChatServiceException(String message) {
        super(message);
    }

    public ChatServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChatServiceException(Throwable cause) {
        super(cause);
    }

}
