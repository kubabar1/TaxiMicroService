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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    private static UserResponseDTO user1;

    @BeforeAll
    static void initBeforeAll() {
        RoleDTO passengerRole = new RoleDTO();
        passengerRole.setId(1L);
        passengerRole.setName("passenger");

        RoleDTO driverRole = new RoleDTO();
        driverRole.setId(2L);
        driverRole.setName("driver");

        RoleDTO adminRole = new RoleDTO();
        adminRole.setId(3L);
        adminRole.setName("admin");

        RoleDTO accountantRole = new RoleDTO();
        accountantRole.setId(4L);
        accountantRole.setName("accountant");

        Set<RoleDTO> userRolesSet = new HashSet<>();
        userRolesSet.add(passengerRole);
        userRolesSet.add(driverRole);
        userRolesSet.add(adminRole);
        userRolesSet.add(accountantRole);

        AppearanceDTO appearanceDTO = new AppearanceDTO();
        appearanceDTO.setAppearanceCode("lt");
        appearanceDTO.setName("light");

        LanguageDTO languageDTO = new LanguageDTO();
        languageDTO.setLanguageCode("en");
        languageDTO.setName("English");

        UserSettingsResponseDTO userSettingsResponseDTO = new UserSettingsResponseDTO();
        userSettingsResponseDTO.setAppearance(appearanceDTO);
        userSettingsResponseDTO.setLanguage(languageDTO);

        String str = "2013-02-14 06:01:17";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);

        user1 = new UserResponseDTO();
        user1.setId(1L);
        user1.setUserName("adam123");
        user1.setName("Adam");
        user1.setSurname("Kowalski");
        user1.setEmail("adam@qwerty.com");
        user1.setPassword("qwerty");
        user1.setPesel("92111000000");
        user1.setBirthDate(LocalDate.parse("1992-11-10"));
        user1.setCreationDate(dateTime);
        user1.setUserRole(userRolesSet);
        user1.setUserSettings(userSettingsResponseDTO);
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
        assertEquals("asdfgh", userResponseDTO.getPassword());
        assertEquals(LocalDate.parse("1991-02-03"), userResponseDTO.getBirthDate());

        assertThrows(EntityNotFoundException.class, () -> userService.updateUserWithId(100L, userUpdateDTO));
    }

}