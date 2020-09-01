package com.taximicroservice.bookingservice.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class BookingAddDTO implements Serializable {

    @NotNull
    private Long passengerId;

    @NotNull
    private Long driverId;

    @NotNull
    private Long startPoint;

    @NotNull
    private Long finishPoint;

}
