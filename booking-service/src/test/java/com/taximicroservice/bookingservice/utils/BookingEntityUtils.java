package com.taximicroservice.bookingservice.utils;

import com.taximicroservice.bookingservice.model.entity.BookingEntity;
import com.taximicroservice.bookingservice.model.entity.BookingStatusEntity;
import com.taximicroservice.bookingservice.model.utils.BookingStatusEnum;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

public class BookingEntityUtils extends BookingTestUtils {

    private BookingEntityUtils() {
    }

    public static class Builder {

        private Long id;

        private Long passengerId;

        private Long driverId;

        private Point startPoint;

        private Point finishPoint;

        private LocalDateTime creationDate;

        private BookingStatusEntity status;


        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setPassengerId(Long passengerId) {
            this.passengerId = passengerId;
            return this;
        }

        public Builder setDriverId(Long driverId) {
            this.driverId = driverId;
            return this;
        }

        public Builder setStartPoint(double longitude, double latitude) {
            this.startPoint = GEOMETRY_FACTORY.createPoint(new Coordinate(latitude, longitude));
            return this;
        }

        public Builder setFinishPoint(double longitude, double latitude) {
            this.finishPoint = GEOMETRY_FACTORY.createPoint(new Coordinate(latitude, longitude));
            return this;
        }

        public Builder setCreationDate(String creationDateString) {
            this.creationDate = LocalDateTime.parse(creationDateString, DATE_TIME_FORMATTER);
            return this;
        }

        public Builder setBookingStatus(BookingStatusEnum bookingStatusEnum) {
            this.status = getBookingStatusEntity(bookingStatusEnum);
            return this;
        }

        public BookingEntity build() {
            BookingEntity bookingEntity = new BookingEntity();
            bookingEntity.setId(id);
            bookingEntity.setPassengerId(passengerId);
            bookingEntity.setDriverId(driverId);
            bookingEntity.setStartPoint(startPoint);
            bookingEntity.setFinishPoint(finishPoint);
            bookingEntity.setStatus(status);
            bookingEntity.setCreationDate(creationDate);
            return bookingEntity;
        }

    }

}




