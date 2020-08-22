package com.taximicroservice.userservice.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LanguageDTO {

    @NotNull
    private String languageCode;

    @NotNull
    private String name;

}
