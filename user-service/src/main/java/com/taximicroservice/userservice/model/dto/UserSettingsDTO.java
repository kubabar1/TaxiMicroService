package com.taximicroservice.userservice.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserSettingsDTO {

    @NotNull
    private String appearanceCode;

    @NotNull
    private String languageCode;

}
