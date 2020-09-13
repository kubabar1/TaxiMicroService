package com.taximicroservice.bookingservice.utils;

import com.taximicroservice.bookingservice.model.dto.BookingResponseDTO;
import com.taximicroservice.bookingservice.model.utils.BookingStatusEnum;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BookingResponseUtils extends BookingTestUtils {

    private BookingResponseUtils() {
    }

    public static class Builder {

        private Long bookingId = 1L;

        private Long passengerId;

        private Long driverId;

        private BookingStatusEnum bookingStatusEnum;

        private LocalDateTime creationDate;

        private Point startPoint;

        private Point finishPoint;

        public Builder setId(Long bookingId) {
            this.bookingId = bookingId;
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

        public Builder setBookingStatus(BookingStatusEnum bookingStatusEnum) {
            this.bookingStatusEnum = bookingStatusEnum;
            return this;
        }

        public Builder setCreationDate(String creationDateString) {
            this.creationDate = LocalDateTime.parse(creationDateString, DATE_TIME_FORMATTER);
            return this;
        }

        public Builder setStartPoint(double longitude, double latitude) {
            this.startPoint = GEOMETRY_FACTORY.createPoint(new Coordinate(longitude, latitude));
            return this;
        }

        public Builder setFinishPoint(double longitude, double latitude) {
            this.finishPoint = GEOMETRY_FACTORY.createPoint(new Coordinate(longitude, latitude));
            return this;
        }

        public BookingResponseDTO build() {
            BookingResponseDTO bookingResponseDTO = new BookingResponseDTO();
            bookingResponseDTO.setId(bookingId);
            bookingResponseDTO.setPassengerId(passengerId);
            bookingResponseDTO.setDriverId(driverId);
            bookingResponseDTO.setStatus(getBookingStatusResponseDTO(bookingStatusEnum));
            bookingResponseDTO.setCreationDate(creationDate);
            bookingResponseDTO.setStartPoint(startPoint);
            bookingResponseDTO.setFinishPoint(finishPoint);
            return bookingResponseDTO;
        }

    }

}