package com.taximicroservice.userservice.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AppearanceDTO {

    @NotNull
    private String appearanceCode;

    @NotNull
    private String name;

}
