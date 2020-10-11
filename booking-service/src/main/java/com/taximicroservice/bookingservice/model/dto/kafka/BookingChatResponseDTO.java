package com.taximicroservice.bookingservice.model.dto.kafka;

import com.taximicroservice.bookingservice.model.utils.BookingStatusEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class BookingChatResponseDTO implements Serializable {

    private String driverUsername;

    private String passengerUsername;

    private BookingStatusEnum bookingStatus;

}
