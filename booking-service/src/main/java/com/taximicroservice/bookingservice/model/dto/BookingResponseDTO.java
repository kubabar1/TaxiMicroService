package com.taximicroservice.bookingservice.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class BookingResponseDTO implements Serializable {

    private Long id;

    private Long passengerId;

    private Long driverId;

    private Long startPoint;

    private Long finishPoint;

    private LocalDateTime creationDate;

    private String status;

}
