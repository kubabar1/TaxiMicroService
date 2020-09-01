package com.taximicroservice.userservice.service.impl;

import com.taximicroservice.userservice.exception.UserServiceException;
import com.taximicroservice.userservice.model.dto.UserAddDTO;
import com.taximicroservice.userservice.model.dto.UserResponseDTO;
import com.taximicroservice.userservice.model.dto.kafka.PageRequestDTO;
import com.taximicroservice.userservice.model.dto.kafka.UserResponseDTOPage;
import com.taximicroservice.userservice.model.entity.RoleEntity;
import com.taximicroservice.userservice.model.entity.UserEntity;
import com.taximicroservice.userservice.model.entity.UserSettingsEntity;
import com.taximicroservice.userservice.repository.AppearanceRepository;
import com.taximicroservice.userservice.repository.LanguageRepository;
import com.taximicroservice.userservice.repository.RoleRepository;
import com.taximicroservice.userservice.repository.UserRepository;
import com.taximicroservice.userservice.service.KafkaListenerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@Service
public class KafkaListenerServiceImpl implements KafkaListenerService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AppearanceRepository appearanceRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private ModelMapper modelMapper;


    @KafkaListener(topics = "${userService.kafka.topics.addPassengerTopic}", containerFactory = "requestUserAddDTOListenerContainerFactory")
    @SendTo()
    @Override
    public UserResponseDTO addPassengerListener(UserAddDTO userAddDTO) throws EntityNotFoundException {
        RoleEntity passengerRoleEntity = roleRepository.findById(1L).orElseThrow(EntityNotFoundException::new);

        UserEntity userEntity = modelMapper.map(userAddDTO, UserEntity.class);
        userEntity.setCreationDate(LocalDateTime.now());
        userEntity.setPasswordSalt("salt"); // TODO: Add salt generation
        userEntity.addRole(passengerRoleEntity);

        UserSettingsEntity userSettingsEntity = new UserSettingsEntity();
        userSettingsEntity.setAppearance(appearanceRepository.findById("dk").orElseThrow(EntityNotFoundException::new));
        userSettingsEntity.setLanguage(languageRepository.findById("en").orElseThrow(EntityNotFoundException::new));
        userEntity.setUserSettings(userSettingsEntity);
        userSettingsEntity.setUser(userEntity);

        userRepository.save(userEntity);

        return modelMapper.map(userEntity, UserResponseDTO.class);
    }

    @KafkaListener(topics = "${userService.kafka.topics.getPassengersPageTopic}", containerFactory = "requestUserResponseDTOPageListenerContainerFactory")
    @SendTo()
    @Override
    public UserResponseDTOPage getPassengersPageListener(PageRequestDTO pageRequestDTO) throws UserServiceException {
        int page = pageRequestDTO.getPage();
        int count = pageRequestDTO.getCount();

        if (page < 0) {
            throw new UserServiceException("Page must be greater than or equal 0");
        }
        if (count <= 0) {
            throw new UserServiceException("Count must be greater than 0");
        }

        Page<UserResponseDTO> userResponseDTOPage = userRepository
                .findByRoleEntitySet_id(1L, PageRequest.of(page, count))
                .map(userEntity -> modelMapper.map(userEntity, UserResponseDTO.class));

        return new UserResponseDTOPage(userResponseDTOPage);
    }

    @KafkaListener(topics = "${userService.kafka.topics.addDriverTopic}", containerFactory = "requestUserAddDTOListenerContainerFactory")
    @SendTo()
    @Override
    public UserResponseDTO addDriverListener(UserAddDTO userAddDTO) throws EntityNotFoundException {
        RoleEntity driverRoleEntity = roleRepository.findById(2L).orElseThrow(EntityNotFoundException::new);

        UserEntity userEntity = modelMapper.map(userAddDTO, UserEntity.class);
        userEntity.setCreationDate(LocalDateTime.now());
        userEntity.setPasswordSalt("salt"); // TODO: Add salt generation
        userEntity.addRole(driverRoleEntity);

        UserSettingsEntity userSettingsEntity = new UserSettingsEntity();
        userSettingsEntity.setAppearance(appearanceRepository.findById("dk").orElseThrow(EntityNotFoundException::new));
        userSettingsEntity.setLanguage(languageRepository.findById("en").orElseThrow(EntityNotFoundException::new));
        userEntity.setUserSettings(userSettingsEntity);
        userSettingsEntity.setUser(userEntity);

        userRepository.save(userEntity);

        return modelMapper.map(userEntity, UserResponseDTO.class);
    }

    @KafkaListener(topics = "${userService.kafka.topics.getDriversPageTopic}", containerFactory = "requestUserResponseDTOPageListenerContainerFactory")
    @SendTo()
    @Override
    public UserResponseDTOPage getDriversPageListener(PageRequestDTO pageRequestDTO) throws UserServiceException {
        int page = pageRequestDTO.getPage();
        int count = pageRequestDTO.getCount();

        if (page < 0) {
            throw new UserServiceException("Page must be greater than or equal 0");
        }
        if (count <= 0) {
            throw new UserServiceException("Count must be greater than 0");
        }

        Page<UserResponseDTO> userResponseDTOPage = userRepository
                .findByRoleEntitySet_id(2L, PageRequest.of(page, count))
                .map(userEntity -> modelMapper.map(userEntity, UserResponseDTO.class));

        return new UserResponseDTOPage(userResponseDTOPage);
    }

}
