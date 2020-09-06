package com.taximicroservice.bookingservice.model.entity;

import com.bedatadriven.jackson.datatype.jts.serialization.GeometryDeserializer;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.locationtech.jts.geom.Point;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "bookings")
public class BookingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    // @JoinColumn(name = "start_point")
    // private LocalisationEntity startPoint;

    // @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    // @JoinColumn(name = "finish_point")
    // private LocalisationEntity finishPoint;

    @Column(name = "start_point", columnDefinition = "Point", nullable = false)
    private Point startPoint;

    @Column(name = "finish_point", columnDefinition = "Point", nullable = false)
    private Point finishPoint;

    @Column(name = "passenger_id", nullable = false)
    private Long passengerId;

    @Column(name = "driver_id")
    private Long driverId;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "status", nullable = false)
    private BookingStatusEntity status;

}
