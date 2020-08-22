package com.taximicroservice.userservice.controller;

import com.taximicroservice.userservice.exception.UserServiceException;
import com.taximicroservice.userservice.model.dto.UserResponseDTO;
import com.taximicroservice.userservice.model.dto.UserUpdateDTO;
import com.taximicroservice.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Page<UserResponseDTO>> getUsersPage(@RequestParam(value = "page") int page,
                                                              @RequestParam(value = "count") int count) {
        Page<UserResponseDTO> userResponseDTOPage;
        try {
            userResponseDTOPage = userService.getUsersPage(page, count);
        } catch (UserServiceException e) {
            return ResponseEntity.unprocessableEntity().build();
        }

        if (userResponseDTOPage.getNumberOfElements() == 0) {
            return new ResponseEntity<>(userResponseDTOPage, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(userResponseDTOPage, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable(value = "userId") Long userId) {
        try {
            return new ResponseEntity<>(userService.getUserWithId(userId), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserResponseDTO> deleteUserById(@PathVariable(value = "userId") Long userId) {
        try {
            return new ResponseEntity<>(userService.deleteUserWithId(userId), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/{userId}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserUpdateDTO> updateUserWithId(@PathVariable(value = "userId") Long userId,
                                                          @Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        try {
            return new ResponseEntity<>(userService.updateUserWithId(userId, userUpdateDTO), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
