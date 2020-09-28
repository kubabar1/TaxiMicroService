package com.taximicroservice.notificationservice.service.utils;

import com.taximicroservice.notificationservice.exception.NotificationServiceException;

public class ValidationUtil {

    public static void validatePageAndCount(int page, int count) throws NotificationServiceException {
        if (page < 0) {
            throw new NotificationServiceException("Page must be greater than or equal 0");
        }
        if (count <= 0) {
            throw new NotificationServiceException("Count must be greater than 0");
        }
    }


}
