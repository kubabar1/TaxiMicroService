package com.taximicroservice.userservice.service.impl;

import com.taximicroservice.userservice.exception.UserServiceException;
import com.taximicroservice.userservice.model.dto.*;
import com.taximicroservice.userservice.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;

import static com.taximicroservice.utils.UserUtils.generateUserResponseDTO;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    private static UserResponseDTO user1;

    @BeforeAll
    static void initBeforeAll() {
        user1 = generateUserResponseDTO();
    }

    @Test
    @Order(1)
    void deleteUserWithId() {
        assertEquals(user1, userService.getUserWithId(1L));
        userService.deleteUserWithId(11L);
        assertThrows(EntityNotFoundException.class, () -> userService.getUserWithId(11L));
    }

    @Test
    void getUsersPage() {
        Page<UserResponseDTO> userResponseDTOPage = userService.getUsersPage(0, 5);

        assertEquals(2, userResponseDTOPage.getTotalPages());
        assertEquals(10, userResponseDTOPage.getTotalElements());
        assertEquals(0, userResponseDTOPage.getNumber());
        assertEquals(5, userResponseDTOPage.getNumberOfElements());
        assertEquals(5, userResponseDTOPage.getSize());
        assertEquals(user1, userResponseDTOPage.getContent().get(0));

        assertThrows(UserServiceException.class, () -> userService.getUsersPage(0, 0));
        assertThrows(UserServiceException.class, () -> userService.getUsersPage(-1, 0));
        assertThrows(UserServiceException.class, () -> userService.getUsersPage(-1, 10));
    }

    @Test
    void getUserWithId() {
        assertEquals(user1, userService.getUserWithId(1L));
    }

    @Test
    void updateUserWithId() {
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        userUpdateDTO.setName("John");
        userUpdateDTO.setSurname("Wayne");
        userUpdateDTO.setPesel("11111111111");
        userUpdateDTO.setPassword("asdfgh");
        userUpdateDTO.setBirthDate(LocalDate.parse("1991-02-03"));
        userService.updateUserWithId(4L, userUpdateDTO);

        UserResponseDTO userResponseDTO = userService.getUserWithId(4L);

        assertEquals("John", userResponseDTO.getName());
        assertEquals("Wayne", userResponseDTO.getSurname());
        assertEquals("11111111111", userResponseDTO.getPesel());
        assertEquals(LocalDate.parse("1991-02-03"), userResponseDTO.getBirthDate());

        assertThrows(EntityNotFoundException.class, () -> userService.updateUserWithId(100L, userUpdateDTO));
    }

}