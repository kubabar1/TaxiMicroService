package com.taximicroservice.bookingservice.model.dto.kafka;

import com.taximicroservice.bookingservice.model.dto.kafka.AppearanceDTO;
import com.taximicroservice.bookingservice.model.dto.kafka.LanguageDTO;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserSettingsResponseDTO implements Serializable {

    private AppearanceDTO appearance;

    private LanguageDTO language;

}