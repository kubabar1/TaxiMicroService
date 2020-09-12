package com.taximicroservice.bookingservice.utils;

import com.taximicroservice.bookingservice.model.dto.BookingAddDTO;
import com.taximicroservice.bookingservice.model.dto.LocalisationDTO;

public class BookingAddUtils extends BookingTestUtils {

    private BookingAddUtils() {
    }

    public static class Builder {

        private Long passengerId;

        private Long driverId;

        private LocalisationDTO startPoint;

        private LocalisationDTO finishPoint;

        public Builder setPassengerId(Long passengerId) {
            this.passengerId = passengerId;
            return this;
        }

        public Builder setDriverId(Long driverId) {
            this.driverId = driverId;
            return this;
        }

        public Builder setStartPoint(double longitude, double latitude) {
            this.startPoint = new LocalisationDTO();
            this.startPoint.setLatitude(latitude);
            this.startPoint.setLongitude(longitude);
            return this;
        }

        public Builder setFinishPoint(double longitude, double latitude) {
            this.finishPoint = new LocalisationDTO();
            this.finishPoint.setLatitude(latitude);
            this.finishPoint.setLongitude(longitude);
            return this;
        }

        public BookingAddDTO build() {
            BookingAddDTO bookingAddDTO = new BookingAddDTO();
            bookingAddDTO.setPassengerId(passengerId);
            bookingAddDTO.setDriverId(driverId);
            bookingAddDTO.setStartPoint(startPoint);
            bookingAddDTO.setFinishPoint(finishPoint);
            return bookingAddDTO;
        }

    }

}
