package com.taximicroservice.bookingservice.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class LocalisationDTO implements Serializable {

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

}
