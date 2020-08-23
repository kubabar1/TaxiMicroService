package com.taximicroservice.userservice.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UserSettingsDTO implements Serializable {

    @NotNull
    private String appearanceCode;

    @NotNull
    private String languageCode;

}
