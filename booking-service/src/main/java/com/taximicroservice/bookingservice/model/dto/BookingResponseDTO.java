package com.taximicroservice.bookingservice.model.dto;

import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import org.locationtech.jts.geom.Point;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class BookingResponseDTO implements Serializable {

    private Long id;

    private Long passengerId;

    private Long driverId;

    @JsonSerialize(using = GeometrySerializer.class)
    private Point startPoint;

    @JsonSerialize(using = GeometrySerializer.class)
    private Point finishPoint;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
    private LocalDateTime creationDate;

    private BookingStatusResponseDTO status;

}
