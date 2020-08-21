package com.taximicroservice.userservice.service;

import com.taximicroservice.userservice.model.dto.UserSettingsDTO;
import com.taximicroservice.userservice.model.dto.UserSettingsResponseDTO;

import javax.persistence.EntityNotFoundException;

public interface UserSettingsService {

    UserSettingsResponseDTO getUserSettings(Long userId) throws EntityNotFoundException;

    UserSettingsResponseDTO updateUserSettings(Long userId, UserSettingsDTO userSettingsDTO) throws EntityNotFoundException;

}
