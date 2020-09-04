package com.taximicroservice.bookingservice.service.impl;

import com.taximicroservice.bookingservice.exception.BookingServiceException;
import com.taximicroservice.bookingservice.model.dto.BookingAddDTO;
import com.taximicroservice.bookingservice.model.dto.BookingResponseDTO;
import com.taximicroservice.bookingservice.model.dto.LocalisationDTO;
import com.taximicroservice.bookingservice.model.entity.BookingEntity;
import com.taximicroservice.bookingservice.model.entity.BookingStatusEntity;
import com.taximicroservice.bookingservice.model.utils.BookingStatusEnum;
import com.taximicroservice.bookingservice.model.utils.BookingValidator;
import com.taximicroservice.bookingservice.repository.BookingRepository;
import com.taximicroservice.bookingservice.repository.BookingStatusRepository;
import com.taximicroservice.bookingservice.service.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingStatusRepository bookingStatusRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Page<BookingResponseDTO> getBookingsPage(int page, int count) throws BookingServiceException {
        BookingValidator.validatePageAndCount(page, count);
        return bookingRepository.findAll(PageRequest.of(page, count))
                .map(bookingEntity -> modelMapper.map(bookingEntity, BookingResponseDTO.class));
    }

    @Override
    public BookingResponseDTO addBooking(BookingAddDTO bookingAddDTO) throws EntityNotFoundException {
        boolean isDriverSet = Objects.isNull(bookingAddDTO.getDriverId());
        BookingStatusEnum bookingStatusEnum = isDriverSet ? BookingStatusEnum.CREATED : BookingStatusEnum.ASSIGNED;
        BookingStatusEntity bookingStatusEntity = bookingStatusRepository
                .findById(bookingStatusEnum.getName())
                .orElseThrow(EntityNotFoundException::new);

        BookingEntity bookingEntity = modelMapper.map(bookingAddDTO, BookingEntity.class);
        bookingEntity.setCreationDate(LocalDateTime.now());
        bookingEntity.setStatus(bookingStatusEntity);

        return modelMapper.map(bookingRepository.save(bookingEntity), BookingResponseDTO.class);
    }

    @Override
    public BookingResponseDTO getBookingById(Long bookingId) throws EntityNotFoundException {
        return modelMapper.map(bookingRepository.findById(bookingId).orElseThrow(EntityNotFoundException::new), BookingResponseDTO.class);
    }

    @Override
    public Page<BookingResponseDTO> getBookingsAssignedToDriver(Long driverId, int page, int count) throws BookingServiceException, EntityNotFoundException {
        BookingValidator.validatePageAndCount(page, count);
        return bookingRepository.findByDriverIdAndStatus_id(driverId, BookingStatusEnum.ASSIGNED.getName(), PageRequest.of(page, count))
                .map(bookingEntity -> modelMapper.map(bookingEntity, BookingResponseDTO.class));
    }

    @Override
    public Page<BookingResponseDTO> getNearbyCreatedBookings(LocalisationDTO localisationDTO, int distance, int page, int count)
            throws BookingServiceException {
        BookingValidator.validatePageAndCount(page, count);
        return null;
    }

}
