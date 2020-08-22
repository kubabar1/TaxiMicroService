package com.taximicroservice.userservice.service.impl;

import com.taximicroservice.userservice.model.dto.AppearanceDTO;
import com.taximicroservice.userservice.model.dto.LanguageDTO;
import com.taximicroservice.userservice.model.dto.UserSettingsDTO;
import com.taximicroservice.userservice.model.dto.UserSettingsResponseDTO;
import com.taximicroservice.userservice.service.UserSettingsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserSettingsServiceImplTest {

    @Autowired
    private UserSettingsService userSettingsService;


    @Test
    void getUserSettings() {
        UserSettingsResponseDTO userSettingsResponseDTO = userSettingsService.getUserSettings(1L);
        AppearanceDTO appearanceDTO = userSettingsResponseDTO.getAppearance();
        LanguageDTO languageDTO = userSettingsResponseDTO.getLanguage();

        assertEquals("lt", appearanceDTO.getAppearanceCode());
        assertEquals("light", appearanceDTO.getName());

        assertEquals("en", languageDTO.getLanguageCode());
        assertEquals("English", languageDTO.getName());
    }

    @Test
    void updateUserSettings() {
        UserSettingsDTO userSettingsDTO = new UserSettingsDTO();
        userSettingsDTO.setAppearanceCode("dk");
        userSettingsDTO.setLanguageCode("ru");

        userSettingsService.updateUserSettings(6L, userSettingsDTO);

        UserSettingsResponseDTO userSettingsResponseDTO = userSettingsService.getUserSettings(6L);
        AppearanceDTO appearanceDTO = userSettingsResponseDTO.getAppearance();
        LanguageDTO languageDTO = userSettingsResponseDTO.getLanguage();

        assertEquals("dk", appearanceDTO.getAppearanceCode());
        assertEquals("dark", appearanceDTO.getName());

        assertEquals("ru", languageDTO.getLanguageCode());
        assertEquals("Russian", languageDTO.getName());


        UserSettingsDTO userSettingsDTO2 = new UserSettingsDTO();
        userSettingsDTO2.setAppearanceCode("not_correct");
        userSettingsDTO2.setLanguageCode("ru");

        assertThrows(EntityNotFoundException.class, () -> userSettingsService.updateUserSettings(6L, userSettingsDTO2));


        UserSettingsDTO userSettingsDTO3 = new UserSettingsDTO();
        userSettingsDTO3.setAppearanceCode("dk");
        userSettingsDTO3.setLanguageCode("not_correct");

        assertThrows(EntityNotFoundException.class, () -> userSettingsService.updateUserSettings(6L, userSettingsDTO3));


        UserSettingsDTO userSettingsDTO4 = new UserSettingsDTO();
        userSettingsDTO4.setAppearanceCode("not_correct");
        userSettingsDTO4.setLanguageCode("not_correct");

        assertThrows(EntityNotFoundException.class, () -> userSettingsService.updateUserSettings(6L, userSettingsDTO4));
    }

}