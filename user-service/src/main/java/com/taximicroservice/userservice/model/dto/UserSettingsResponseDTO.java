package com.taximicroservice.userservice.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserSettingsResponseDTO {

    @NotNull
    private AppearanceDTO appearance;

    @NotNull
    private LanguageDTO language;

}
