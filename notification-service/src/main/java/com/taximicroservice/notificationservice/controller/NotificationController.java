package com.taximicroservice.notificationservice.controller;

import com.taximicroservice.notificationservice.exception.NotificationServiceException;
import com.taximicroservice.notificationservice.model.dto.NotificationResponseDTO;
import com.taximicroservice.notificationservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping(value = "/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;


    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Page<NotificationResponseDTO>> getNotifications(@RequestParam(value = "page") int page,
                                                                          @RequestParam(value = "count") int count) {
        Page<NotificationResponseDTO> notificationResponseDTORestPage;

        try {
            notificationResponseDTORestPage = notificationService.getNotifications(page, count);
        } catch (NotificationServiceException e) {
            return ResponseEntity.unprocessableEntity().build();
        }

        if (notificationResponseDTORestPage.getNumberOfElements() == 0) {
            return new ResponseEntity<>(notificationResponseDTORestPage, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(notificationResponseDTORestPage, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/{notificationId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<NotificationResponseDTO> getNotificationById(@PathVariable(value = "notificationId") Long notificationId) {
        try {
            return new ResponseEntity<>(notificationService.getNotificationById(notificationId), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/username/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Page<NotificationResponseDTO>> getNotificationsByUserId(@PathVariable(value = "userId") String userId,
                                                                                  @RequestParam(value = "page") int page,
                                                                                  @RequestParam(value = "count") int count) {
        Page<NotificationResponseDTO> notificationResponseDTORestPage;

        try {
            notificationResponseDTORestPage = notificationService.getNotificationsByReceiverUsername(userId, page, count);
        } catch (NotificationServiceException e) {
            return ResponseEntity.unprocessableEntity().build();
        }

        if (notificationResponseDTORestPage.getNumberOfElements() == 0) {
            return new ResponseEntity<>(notificationResponseDTORestPage, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(notificationResponseDTORestPage, HttpStatus.OK);
        }
    }

}
