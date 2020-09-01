package com.taximicroservice.driverservice.utils;

import com.taximicroservice.driverservice.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class DriverUtils {

    public static DriverResponseDTO generateDriverResponseDTO() {
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

        DriverResponseDTO user = new DriverResponseDTO();
        user.setId(1L);
        user.setUserName("adam123");
        user.setName("Adam");
        user.setSurname("Kowalski");
        user.setEmail("adam@qwerty.com");
        user.setPesel("92111000000");
        user.setBirthDate(LocalDate.parse("1992-11-10"));
        user.setCreationDate(dateTime);
        user.setUserRole(userRolesSet);
        user.setUserSettings(userSettingsResponseDTO);

        return user;
    }

}
