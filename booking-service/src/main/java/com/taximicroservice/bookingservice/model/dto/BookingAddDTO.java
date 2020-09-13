package com.taximicroservice.bookingservice.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class BookingAddDTO implements Serializable {

    @NotNull
    private Long passengerId;

    private Long driverId;

    @NotNull
    private LocalisationDTO startPoint;

    @NotNull
    private LocalisationDTO finishPoint;

}
