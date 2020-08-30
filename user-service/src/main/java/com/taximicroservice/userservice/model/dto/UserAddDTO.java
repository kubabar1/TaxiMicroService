package com.taximicroservice.userservice.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class UserAddDTO implements Serializable {

    @NotNull
    @NotEmpty
    private String userName;

    @NotNull
    @NotEmpty
    private String email;

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
    @NotEmpty
    private String password;

    @NotNull
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate birthDate;

}
