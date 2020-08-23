package com.taximicroservice.userservice.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UserSettingsResponseDTO implements Serializable {

    @NotNull
    private AppearanceDTO appearance;

    @NotNull
    private LanguageDTO language;

}
