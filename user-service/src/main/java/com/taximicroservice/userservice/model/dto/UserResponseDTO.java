package com.taximicroservice.userservice.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class UserResponseDTO {

    private Long id;

    private String userName;

    private String email;

    private String name;

    private String surname;

    private String pesel;

    private LocalDate birthDate;

    private String password;

    private LocalDateTime creationDate;

    private UserSettingsResponseDTO userSettings;

    private Set<RoleDTO> userRole;

}
