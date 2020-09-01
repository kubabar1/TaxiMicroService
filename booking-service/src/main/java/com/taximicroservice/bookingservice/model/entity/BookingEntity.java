package com.taximicroservice.bookingservice.model.entity;

import lombok.Data;

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

    @Column(name = "start_point")
    private Long startPoint;

    @Column(name = "finish_point")
    private Long finishPoint;

    @Column(name = "passenger_id")
    private Long passengerId;

    @Column(name = "driver_id")
    private Long driverId;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "status")
    private String status;

}
