package com.taximicroservice.userservice.controller;

import com.taximicroservice.userservice.model.dto.UserSettingsDTO;
import com.taximicroservice.userservice.model.dto.UserSettingsResponseDTO;
import com.taximicroservice.userservice.service.UserSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/users/settings")
public class UserSettingsController {

    @Autowired
    private UserSettingsService userSettingsService;

    @GetMapping(value = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserSettingsResponseDTO> getUserSettings(@PathVariable("userId") Long userId) {
        try {
            return new ResponseEntity<>(userSettingsService.getUserSettings(userId), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/{userId}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserSettingsResponseDTO> updateUserSettings(@PathVariable("userId") Long userId,
                                                                      @RequestBody UserSettingsDTO userSettingsDTO) {
        try {
            return new ResponseEntity<>(userSettingsService.updateUserSettings(userId, userSettingsDTO), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
