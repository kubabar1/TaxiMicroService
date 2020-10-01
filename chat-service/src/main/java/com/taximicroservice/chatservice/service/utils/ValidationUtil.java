package com.taximicroservice.chatservice.service.utils;

import com.taximicroservice.chatservice.exception.ChatServiceException;
import org.springframework.stereotype.Component;

@Component
public class ValidationUtil {

    public void validatePageAndCount(int page, int count) throws ChatServiceException {
        if (page < 0) {
            throw new ChatServiceException("Page must be greater than or equal 0");
        }
        if (count <= 0) {
            throw new ChatServiceException("Count must be greater than 0");
        }
    }

}
