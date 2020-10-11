package com.taximicroservice.bookingservice.service;

import com.taximicroservice.bookingservice.model.dto.kafka.BookingChatResponseDTO;

public interface BookingChatDataService {

     BookingChatResponseDTO getBookingChatDataById(Long bookingId);

}
