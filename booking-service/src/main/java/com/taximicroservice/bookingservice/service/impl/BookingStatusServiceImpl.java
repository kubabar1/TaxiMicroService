package com.taximicroservice.bookingservice.service.impl;

import com.taximicroservice.bookingservice.model.dto.BookingResponseDTO;
import com.taximicroservice.bookingservice.model.entity.BookingEntity;
import com.taximicroservice.bookingservice.model.entity.BookingStatusEntity;
import com.taximicroservice.bookingservice.model.utils.BookingStatusEnum;
import com.taximicroservice.bookingservice.repository.BookingRepository;
import com.taximicroservice.bookingservice.repository.BookingStatusRepository;
import com.taximicroservice.bookingservice.service.BookingStatusService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class BookingStatusServiceImpl implements BookingStatusService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingStatusRepository bookingStatusRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public BookingResponseDTO assignDriverToBooking(Long driverId, Long bookingId) throws EntityNotFoundException {
        BookingEntity bookingEntity = bookingRepository.findById(bookingId).orElseThrow(EntityNotFoundException::new);
        BookingStatusEntity bookingStatusEntity = bookingStatusRepository
                .findById(BookingStatusEnum.ASSIGNED.getName())
                .orElseThrow(EntityNotFoundException::new);
        bookingEntity.setStatus(bookingStatusEntity);
        bookingEntity.setDriverId(driverId);
        bookingRepository.save(bookingEntity);
        return modelMapper.map(bookingEntity, BookingResponseDTO.class);
    }

    @Override
    public BookingResponseDTO abortBooking(Long bookingId) {
        BookingEntity bookingEntity = bookingRepository.findById(bookingId).orElseThrow(EntityNotFoundException::new);
        BookingStatusEntity bookingStatusEntity = bookingStatusRepository
                .findById(BookingStatusEnum.ABORTED.getName())
                .orElseThrow(EntityNotFoundException::new);
        bookingEntity.setStatus(bookingStatusEntity);
        bookingRepository.save(bookingEntity);
        return modelMapper.map(bookingEntity, BookingResponseDTO.class);
    }

    @Override
    public BookingResponseDTO cancelBooking(Long bookingId) {
        BookingEntity bookingEntity = bookingRepository.findById(bookingId).orElseThrow(EntityNotFoundException::new);
        BookingStatusEntity bookingStatusEntity = bookingStatusRepository
                .findById(BookingStatusEnum.CANCELED.getName())
                .orElseThrow(EntityNotFoundException::new);
        bookingEntity.setStatus(bookingStatusEntity);
        bookingRepository.save(bookingEntity);
        return modelMapper.map(bookingEntity, BookingResponseDTO.class);
    }

    @Override
    public BookingResponseDTO finishBooking(Long bookingId) {
        BookingEntity bookingEntity = bookingRepository.findById(bookingId).orElseThrow(EntityNotFoundException::new);
        BookingStatusEntity bookingStatusEntity = bookingStatusRepository
                .findById(BookingStatusEnum.FINISHED.getName())
                .orElseThrow(EntityNotFoundException::new);
        bookingEntity.setStatus(bookingStatusEntity);
        bookingRepository.save(bookingEntity);
        return modelMapper.map(bookingEntity, BookingResponseDTO.class);
    }

    @Override
    public BookingResponseDTO removeBooking(Long bookingId) {
        BookingEntity bookingEntity = bookingRepository.findById(bookingId).orElseThrow(EntityNotFoundException::new);
        BookingStatusEntity bookingStatusEntity = bookingStatusRepository
                .findById(BookingStatusEnum.REMOVED.getName())
                .orElseThrow(EntityNotFoundException::new);
        bookingEntity.setStatus(bookingStatusEntity);
        bookingRepository.save(bookingEntity);
        return modelMapper.map(bookingEntity, BookingResponseDTO.class);
    }

    @Override
    public BookingResponseDTO startBookingProgress(Long bookingId) {
        BookingEntity bookingEntity = bookingRepository.findById(bookingId).orElseThrow(EntityNotFoundException::new);
        BookingStatusEntity bookingStatusEntity = bookingStatusRepository
                .findById(BookingStatusEnum.IN_PROGRESS.getName())
                .orElseThrow(EntityNotFoundException::new);
        bookingEntity.setStatus(bookingStatusEntity);
        bookingRepository.save(bookingEntity);
        return modelMapper.map(bookingEntity, BookingResponseDTO.class);
    }

}
