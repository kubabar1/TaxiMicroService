package com.taximicroservice.userservice.model.dto;

import com.taximicroservice.userservice.model.types.AppearanceType;
import lombok.Data;

@Data
public class UserSettingsDTO {

    private AppearanceType appearance;

    private String language;

}
