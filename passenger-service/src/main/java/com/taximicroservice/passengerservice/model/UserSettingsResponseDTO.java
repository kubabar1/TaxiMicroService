package com.taximicroservice.passengerservice.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserSettingsResponseDTO implements Serializable {

    private AppearanceDTO appearance;

    private LanguageDTO language;

}
