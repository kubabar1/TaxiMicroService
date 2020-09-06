package com.taximicroservice.bookingservice.model.dto;

import com.bedatadriven.jackson.datatype.jts.serialization.GeometryDeserializer;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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

    private LocalDateTime creationDate;

    private BookingStatusResponseDTO status;

}
