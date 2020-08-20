package com.taximicroservice.userservice.service.impl;

import com.taximicroservice.userservice.model.dto.UserSettingsDTO;
import com.taximicroservice.userservice.model.dto.UserSettingsResponseDTO;
import com.taximicroservice.userservice.model.entity.AppearanceEntity;
import com.taximicroservice.userservice.model.entity.LanguageEntity;
import com.taximicroservice.userservice.model.entity.UserSettingsEntity;
import com.taximicroservice.userservice.repository.AppearanceRepository;
import com.taximicroservice.userservice.repository.LanguageRepository;
import com.taximicroservice.userservice.repository.UserSettingsRepository;
import com.taximicroservice.userservice.service.UserSettingsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class UserSettingsServiceImpl implements UserSettingsService {

    @Autowired
    private UserSettingsRepository userSettingsRepository;

    @Autowired
    private AppearanceRepository appearanceRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserSettingsResponseDTO getUserSettings(Long userId) {
        return modelMapper.map(userSettingsRepository.findById(userId).orElseThrow(EntityNotFoundException::new), UserSettingsResponseDTO.class);
    }

    @Override
    public UserSettingsResponseDTO updateUserSettings(Long userId, UserSettingsDTO userSettingsDTO) {
        UserSettingsEntity userSettingsEntity = userSettingsRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        userSettingsEntity.setLanguage(languageRepository.findById(userSettingsDTO.getLanguageCode()).orElseThrow(EntityNotFoundException::new));
        userSettingsEntity.setAppearance(appearanceRepository.findById(userSettingsDTO.getAppearanceCode()).orElseThrow(EntityNotFoundException::new));
        userSettingsRepository.save(userSettingsEntity);
        return modelMapper.map(userSettingsEntity, UserSettingsResponseDTO.class);
    }
}
