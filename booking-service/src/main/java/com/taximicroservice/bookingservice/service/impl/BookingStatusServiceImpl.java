package com.taximicroservice.bookingservice.service.impl;

import com.taximicroservice.bookingservice.exception.BookingServiceException;
import com.taximicroservice.bookingservice.model.dto.BookingResponseDTO;
import com.taximicroservice.bookingservice.model.entity.BookingEntity;
import com.taximicroservice.bookingservice.model.entity.BookingStatusEntity;
import com.taximicroservice.bookingservice.model.utils.BookingStatusEnum;
import com.taximicroservice.bookingservice.service.utils.BookingValidator;
import com.taximicroservice.bookingservice.repository.BookingRepository;
import com.taximicroservice.bookingservice.repository.BookingStatusRepository;
import com.taximicroservice.bookingservice.service.BookingStatusService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import java.util.Collections;

import static java.util.Arrays.asList;

@Service
public class BookingStatusServiceImpl implements BookingStatusService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingStatusRepository bookingStatusRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BookingValidator bookingValidator;


    @Override
    public BookingResponseDTO assignDriverToBooking(Long driverId, Long bookingId) throws BookingServiceException, EntityNotFoundException {
        BookingEntity bookingEntity = bookingRepository.findById(bookingId).orElseThrow(EntityNotFoundException::new);
        bookingValidator.validateBookingCurrentStatus(Collections.singletonList(BookingStatusEnum.CREATED.getName()),
                bookingEntity.getStatus().getId());

        BookingStatusEntity bookingStatusEntity = bookingStatusRepository
                .findById(BookingStatusEnum.ASSIGNED.getName())
                .orElseThrow(EntityNotFoundException::new);
        bookingEntity.setStatus(bookingStatusEntity);
        bookingEntity.setDriverId(driverId);
        bookingRepository.save(bookingEntity);
        return modelMapper.map(bookingEntity, BookingResponseDTO.class);
    }

    @Override
    public BookingResponseDTO unassignDriverFromBooking(Long bookingId) throws BookingServiceException, EntityNotFoundException {
        BookingEntity bookingEntity = bookingRepository.findById(bookingId).orElseThrow(EntityNotFoundException::new);
        bookingValidator.validateBookingCurrentStatus(Collections.singletonList(BookingStatusEnum.ASSIGNED.getName()),
                bookingEntity.getStatus().getId());

        BookingStatusEntity bookingStatusEntity = bookingStatusRepository
                .findById(BookingStatusEnum.CREATED.getName())
                .orElseThrow(EntityNotFoundException::new);
        bookingEntity.setDriverId(null);
        bookingEntity.setStatus(bookingStatusEntity);
        bookingRepository.save(bookingEntity);
        return modelMapper.map(bookingEntity, BookingResponseDTO.class);
    }

    @Override
    public BookingResponseDTO abortBooking(Long bookingId) throws BookingServiceException, EntityNotFoundException {
        BookingEntity bookingEntity = bookingRepository.findById(bookingId).orElseThrow(EntityNotFoundException::new);
        bookingValidator.validateBookingCurrentStatus(Collections.singletonList(BookingStatusEnum.IN_PROGRESS.getName()),
                bookingEntity.getStatus().getId());

        BookingStatusEntity bookingStatusEntity = bookingStatusRepository
                .findById(BookingStatusEnum.ABORTED.getName())
                .orElseThrow(EntityNotFoundException::new);
        bookingEntity.setStatus(bookingStatusEntity);
        bookingRepository.save(bookingEntity);
        return modelMapper.map(bookingEntity, BookingResponseDTO.class);
    }

    @Override
    public BookingResponseDTO cancelBooking(Long bookingId) throws BookingServiceException, EntityNotFoundException {
        BookingEntity bookingEntity = bookingRepository.findById(bookingId).orElseThrow(EntityNotFoundException::new);
        bookingValidator.validateBookingCurrentStatus(asList(BookingStatusEnum.CREATED.getName(), BookingStatusEnum.ASSIGNED.getName()),
                bookingEntity.getStatus().getId());

        BookingStatusEntity bookingStatusEntity = bookingStatusRepository
                .findById(BookingStatusEnum.CANCELED.getName())
                .orElseThrow(EntityNotFoundException::new);
        bookingEntity.setStatus(bookingStatusEntity);
        bookingRepository.save(bookingEntity);
        return modelMapper.map(bookingEntity, BookingResponseDTO.class);
    }

    @Override
    public BookingResponseDTO finishBooking(Long bookingId) throws BookingServiceException, EntityNotFoundException {
        BookingEntity bookingEntity = bookingRepository.findById(bookingId).orElseThrow(EntityNotFoundException::new);
        bookingValidator.validateBookingCurrentStatus(Collections.singletonList(BookingStatusEnum.IN_PROGRESS.getName()),
                bookingEntity.getStatus().getId());

        BookingStatusEntity bookingStatusEntity = bookingStatusRepository
                .findById(BookingStatusEnum.FINISHED.getName())
                .orElseThrow(EntityNotFoundException::new);
        bookingEntity.setStatus(bookingStatusEntity);
        bookingRepository.save(bookingEntity);
        return modelMapper.map(bookingEntity, BookingResponseDTO.class);
    }

    @Override
    public BookingResponseDTO startBookingProgress(Long bookingId) throws BookingServiceException, EntityNotFoundException {
        BookingEntity bookingEntity = bookingRepository.findById(bookingId).orElseThrow(EntityNotFoundException::new);
        bookingValidator.validateBookingCurrentStatus(Collections.singletonList(BookingStatusEnum.ASSIGNED.getName()),
                bookingEntity.getStatus().getId());

        BookingStatusEntity bookingStatusEntity = bookingStatusRepository
                .findById(BookingStatusEnum.IN_PROGRESS.getName())
                .orElseThrow(EntityNotFoundException::new);
        bookingEntity.setStatus(bookingStatusEntity);
        bookingRepository.save(bookingEntity);
        return modelMapper.map(bookingEntity, BookingResponseDTO.class);
    }

}
