package com.taximicroservice.userservice.service;

import com.taximicroservice.userservice.model.dto.UserSettingsDTO;
import com.taximicroservice.userservice.model.dto.UserSettingsResponseDTO;

public interface UserSettingsService {

    UserSettingsResponseDTO getUserSettings(Long userId);

    UserSettingsResponseDTO updateUserSettings(Long userId, UserSettingsDTO userSettingsDTO);

}
