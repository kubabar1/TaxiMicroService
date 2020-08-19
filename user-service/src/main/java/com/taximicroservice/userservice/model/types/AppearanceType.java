package com.taximicroservice.userservice.model.types;

import lombok.Data;

@Data
public enum AppearanceType {
    LIGHT("light"),
    DARK("dark");

    String name;

    AppearanceType(String name) {
        this.name = name;
    }

}
