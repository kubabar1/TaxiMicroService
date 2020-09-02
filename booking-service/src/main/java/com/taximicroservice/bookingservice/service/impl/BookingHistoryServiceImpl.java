package com.taximicroservice.bookingservice.service.impl;

import com.taximicroservice.bookingservice.model.dto.BookingResponseDTO;
import com.taximicroservice.bookingservice.service.BookingHistoryService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class BookingHistoryServiceImpl implements BookingHistoryService {

    @Override
    public Page<BookingResponseDTO> getPreviousDriverBookingsPage(Long bookingId, int page, int count) {
        return null;
    }

    @Override
    public Page<BookingResponseDTO> getPreviousPassengerBookingsPage(Long bookingId, int page, int count) {
        return null;
    }

}
