package com.taximicroservice.userservice.controller;

import com.taximicroservice.userservice.model.dto.UserResponseDTO;
import com.taximicroservice.userservice.model.dto.UserUpdateDTO;
import com.taximicroservice.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserResponseDTO>> getUserList(@RequestParam(value = "page") int page,
                                                             @RequestParam(value = "count") int count) {
        if (page < 0 || count <= 0) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Page<UserResponseDTO> userResponseDTOPage = userService.getUsersPage(page, count);
        if (userResponseDTOPage.getNumberOfElements() == 0) {
            return new ResponseEntity<>(userResponseDTOPage, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(userResponseDTOPage, HttpStatus.OK);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable(value = "userId") Long userId) {
        try {
            return new ResponseEntity<>(userService.getUserWithId(userId), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> deleteUserById(@PathVariable(value = "userId") Long userId) {
        try {
            return new ResponseEntity<>(userService.deleteUserWithId(userId), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserUpdateDTO> updateUserWithId(@PathVariable(value = "userId") Long userId,
                                                            @Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        try {
            return new ResponseEntity<>(userService.updateUserWithId(userId, userUpdateDTO), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
