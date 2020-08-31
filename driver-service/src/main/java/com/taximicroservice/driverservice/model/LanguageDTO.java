package com.taximicroservice.driverservice.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class LanguageDTO implements Serializable {

    private String languageCode;

    private String name;

}
