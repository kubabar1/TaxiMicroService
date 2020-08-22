package com.taximicroservice.userservice.controller;

import com.google.gson.Gson;
import com.taximicroservice.userservice.model.dto.AppearanceDTO;
import com.taximicroservice.userservice.model.dto.LanguageDTO;
import com.taximicroservice.userservice.model.dto.UserSettingsDTO;
import com.taximicroservice.userservice.model.dto.UserSettingsResponseDTO;
import com.taximicroservice.userservice.service.UserSettingsService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserSettingsController.class)
class UserSettingsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserSettingsService userSettingsServiceMock;

    private static UserSettingsResponseDTO userSettingsResponseDTO;

    private static Gson gson;

    @BeforeAll
    static void initBeforeAll() {
        userSettingsResponseDTO = new UserSettingsResponseDTO();
        AppearanceDTO appearanceDTO = new AppearanceDTO();
        appearanceDTO.setAppearanceCode("lt");
        appearanceDTO.setName("light");
        LanguageDTO languageDTO = new LanguageDTO();
        languageDTO.setLanguageCode("en");
        languageDTO.setName("English");
        userSettingsResponseDTO.setAppearance(appearanceDTO);
        userSettingsResponseDTO.setLanguage(languageDTO);

        gson = new Gson();
    }

    @Test
    void getUserSettings() throws Exception {
        when(userSettingsServiceMock.getUserSettings(1L)).thenReturn(userSettingsResponseDTO);
        when(userSettingsServiceMock.getUserSettings(111L)).thenThrow(EntityNotFoundException.class);

        mockMvc.perform(get("/users/settings/{userId}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.appearance.appearanceCode").value("lt"))
                .andExpect(jsonPath("$.appearance.name").value("light"))
                .andExpect(jsonPath("$.language.languageCode").value("en"))
                .andExpect(jsonPath("$.language.name").value("English"));

        mockMvc.perform(get("/users/settings/{userId}", 111L))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateUserSettings() throws Exception {
        UserSettingsDTO userSettingsDTO = new UserSettingsDTO();
        userSettingsDTO.setLanguageCode("ru");
        userSettingsDTO.setAppearanceCode("dk");

        UserSettingsResponseDTO userSettingsResponseAfterUpdateDTO = new UserSettingsResponseDTO();
        AppearanceDTO appearanceDTO = new AppearanceDTO();
        appearanceDTO.setAppearanceCode("dk");
        appearanceDTO.setName("dark");
        LanguageDTO languageDTO = new LanguageDTO();
        languageDTO.setLanguageCode("ru");
        languageDTO.setName("Russian");
        userSettingsResponseAfterUpdateDTO.setAppearance(appearanceDTO);
        userSettingsResponseAfterUpdateDTO.setLanguage(languageDTO);

        when(userSettingsServiceMock.updateUserSettings(1L, userSettingsDTO)).thenReturn(userSettingsResponseAfterUpdateDTO);
        when(userSettingsServiceMock.updateUserSettings(111L, userSettingsDTO)).thenThrow(EntityNotFoundException.class);

        System.out.println(gson.toJson(userSettingsDTO));

        mockMvc.perform(put("/users/settings/{userId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(userSettingsDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.appearance.appearanceCode").value("dk"))
                .andExpect(jsonPath("$.appearance.name").value("dark"))
                .andExpect(jsonPath("$.language.languageCode").value("ru"))
                .andExpect(jsonPath("$.language.name").value("Russian"));

        mockMvc.perform(put("/users/settings/{userId}", 111L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(userSettingsDTO)))
                .andExpect(status().isNotFound());
    }

}