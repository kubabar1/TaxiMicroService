package com.taximicroservice.passengerservice.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class AppearanceDTO implements Serializable {

    private String appearanceCode;

    private String name;

}
