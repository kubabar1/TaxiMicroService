package com.taximicroservice.bookingservice.model.dto.kafka;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class LanguageDTO implements Serializable {

    @NotNull
    private String languageCode;

    @NotNull
    private String name;

}
