package com.taximicroservice.userservice.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class UserUpdateDTO {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String surname;

    @NotNull
    @NotEmpty
    private String pesel;

    @NotNull
    private LocalDate birthDate;

    @NotNull
    @NotEmpty
    private String password;

}
