package com.taximicroservice.bookingservice.model.dto.kafka;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class UserResponseDTO implements Serializable {

    private Long id;

    private String userName;

    private String email;

    private String name;

    private String surname;

    private String pesel;

    private LocalDate birthDate;

    private LocalDateTime creationDate;

    private UserSettingsResponseDTO userSettings;

    private Set<RoleDTO> userRole;

}