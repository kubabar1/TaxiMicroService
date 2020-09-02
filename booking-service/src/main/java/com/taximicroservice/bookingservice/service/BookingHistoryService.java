package com.taximicroservice.bookingservice.service;

import com.taximicroservice.bookingservice.model.dto.BookingResponseDTO;
import org.springframework.data.domain.Page;

public interface BookingHistoryService {

    Page<BookingResponseDTO> getPreviousDriverBookingsPage(Long bookingId, int page, int count);

    Page<BookingResponseDTO> getPreviousPassengerBookingsPage(Long bookingId, int page, int count);

}
