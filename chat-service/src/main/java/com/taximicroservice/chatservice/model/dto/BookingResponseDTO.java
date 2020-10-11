package com.taximicroservice.chatservice.model.dto;

import com.taximicroservice.chatservice.model.utils.BookingStatusEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class BookingResponseDTO implements Serializable {

    private String driverUsername;

    private String passengerUsername;

    private BookingStatusEnum bookingStatus;

}
