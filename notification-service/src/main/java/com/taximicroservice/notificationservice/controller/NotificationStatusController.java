package com.taximicroservice.notificationservice.controller;

import com.taximicroservice.notificationservice.exception.NotificationServiceException;
import com.taximicroservice.notificationservice.model.dto.NotificationResponseDTO;
import com.taximicroservice.notificationservice.service.NotificationStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping(value = "/notifications-status")
public class NotificationStatusController {

    @Autowired
    private NotificationStatusService notificationStatusService;


    @PutMapping(value = "/read/{notificationId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<NotificationResponseDTO> setNotificationAsRead(@PathVariable(value = "notificationId") Long notificationId) {
        try {
            return new ResponseEntity<>(notificationStatusService.setNotificationAsRead(notificationId), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (NotificationServiceException e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @PutMapping(value = "/delete/{notificationId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<NotificationResponseDTO> setNotificationAsDeleted(@PathVariable(value = "notificationId") Long notificationId) {
        try {
            return new ResponseEntity<>(notificationStatusService.setNotificationAsDeleted(notificationId), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (NotificationServiceException e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

}
