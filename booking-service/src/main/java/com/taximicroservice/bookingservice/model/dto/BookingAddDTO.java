package com.taximicroservice.bookingservice.model.dto;

import com.bedatadriven.jackson.datatype.jts.serialization.GeometryDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

import org.locationtech.jts.geom.Point;

@Data
public class BookingAddDTO implements Serializable {

    @NotNull
    private Long passengerId;

    @NotNull
    private Long driverId;

    @NotNull
    @JsonDeserialize(contentUsing = GeometryDeserializer.class)
    private Point startPoint;

    @NotNull
    @JsonDeserialize(contentUsing = GeometryDeserializer.class)
    private Point finishPoint;

}
