package com.taximicroservice.bookingservice.service.impl;

import com.taximicroservice.bookingservice.exception.BookingServiceException;
import com.taximicroservice.bookingservice.model.dto.BookingResponseDTO;
import com.taximicroservice.bookingservice.model.utils.BookingValidator;
import com.taximicroservice.bookingservice.repository.BookingRepository;
import com.taximicroservice.bookingservice.service.BookingHistoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class BookingHistoryServiceImpl implements BookingHistoryService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BookingValidator bookingValidator;


    @Override
    public Page<BookingResponseDTO> getDriverBookingsHistoryPage(Long driverId, int page, int count) throws BookingServiceException {
        bookingValidator.validatePageAndCount(page, count);
        return bookingRepository.findByDriverId(driverId, PageRequest.of(page, count))
                .map(bookingEntity -> modelMapper.map(bookingEntity, BookingResponseDTO.class));
    }

    @Override
    public Page<BookingResponseDTO> getPassengerBookingsHistoryPage(Long passengerId, int page, int count) throws BookingServiceException {
        bookingValidator.validatePageAndCount(page, count);
        return bookingRepository.findByPassengerId(passengerId, PageRequest.of(page, count))
                .map(bookingEntity -> modelMapper.map(bookingEntity, BookingResponseDTO.class));
    }

}
