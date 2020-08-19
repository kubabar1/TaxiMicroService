package com.taximicroservice.userservice.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserUpdateDTO {

    private String name;

    private String surname;

    private String pesel;

    private LocalDate birthDate;

    private String password;

}
