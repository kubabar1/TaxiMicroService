package com.taximicroservice.bookingservice.repository;

import com.taximicroservice.bookingservice.model.entity.BookingEntity;
import com.taximicroservice.bookingservice.model.utils.BookingStatusEnum;
import com.taximicroservice.bookingservice.utils.BookingEntityUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class BookingRepositoryTest {

    @Autowired
    private BookingRepository bookingRepository;

    private static BookingEntity bookingEntity1;

    private static BookingEntity bookingEntity2;

    @BeforeAll
    static void initBeforeAll() {
        bookingEntity1 = new BookingEntityUtils.Builder()
                .setId(2L)
                .setPassengerId(2L)
                .setDriverId(52L)
                .setBookingStatus(BookingStatusEnum.ASSIGNED)
                .setCreationDate("2020-08-21 07:02:12")
                .setStartPoint(52.251428, 20.998274)
                .setFinishPoint(52.246442, 21.104665)
                .build();
        bookingEntity2 = new BookingEntityUtils.Builder()
                .setId(3L)
                .setPassengerId(12L)
                .setDriverId(52L)
                .setBookingStatus(BookingStatusEnum.ASSIGNED)
                .setCreationDate("2020-08-21 07:02:12")
                .setStartPoint(52.248855, 21.027313)
                .setFinishPoint(52.246422, 21.102365)
                .build();
    }

    @Test
    void findByDriverIdAndStatus_id() {
        Page<BookingEntity> bookingEntityPage = bookingRepository.findByDriverIdAndStatus_id(52L, BookingStatusEnum.ASSIGNED.getName(), PageRequest.of(0, 5));

        assertEquals(1, bookingEntityPage.getTotalPages());
        assertEquals(2, bookingEntityPage.getTotalElements());
        assertEquals(0, bookingEntityPage.getNumber());
        assertEquals(2, bookingEntityPage.getNumberOfElements());
        assertEquals(5, bookingEntityPage.getSize());

        bookingEntityPage = bookingRepository.findByDriverIdAndStatus_id(51L, BookingStatusEnum.ASSIGNED.getName(), PageRequest.of(0, 5));

        assertEquals(1, bookingEntityPage.getTotalPages());
        assertEquals(1, bookingEntityPage.getTotalElements());
        assertEquals(0, bookingEntityPage.getNumber());
        assertEquals(1, bookingEntityPage.getNumberOfElements());
        assertEquals(5, bookingEntityPage.getSize());

        bookingEntityPage = bookingRepository.findByDriverIdAndStatus_id(53L, BookingStatusEnum.ASSIGNED.getName(), PageRequest.of(0, 5));

        assertEquals(0, bookingEntityPage.getTotalPages());
        assertEquals(0, bookingEntityPage.getTotalElements());
        assertEquals(0, bookingEntityPage.getNumber());
        assertEquals(0, bookingEntityPage.getNumberOfElements());
        assertEquals(5, bookingEntityPage.getSize());
    }

    @Test
    void findByDriverId() {
        Page<BookingEntity> bookingEntityPage = bookingRepository.findByDriverId(52L, PageRequest.of(0, 5));

        assertEquals(1, bookingEntityPage.getTotalPages());
        assertEquals(2, bookingEntityPage.getTotalElements());
        assertEquals(0, bookingEntityPage.getNumber());
        assertEquals(2, bookingEntityPage.getNumberOfElements());
        assertEquals(5, bookingEntityPage.getSize());
        assertEquals(bookingEntity1, bookingEntityPage.getContent().get(0));
        assertEquals(bookingEntity2, bookingEntityPage.getContent().get(1));

        assertThrows(IllegalArgumentException.class, () -> bookingRepository.findByDriverId(52L, PageRequest.of(0, 0)));
        assertThrows(IllegalArgumentException.class, () -> bookingRepository.findByDriverId(52L, PageRequest.of(-1, 0)));
        assertThrows(IllegalArgumentException.class, () -> bookingRepository.findByDriverId(52L, PageRequest.of(-1, 10)));
    }

    @Test
    void findByPassengerId() {
        Page<BookingEntity> bookingEntityPage = bookingRepository.findByPassengerId(2L, PageRequest.of(0, 5));

        assertEquals(1, bookingEntityPage.getTotalPages());
        assertEquals(1, bookingEntityPage.getTotalElements());
        assertEquals(0, bookingEntityPage.getNumber());
        assertEquals(1, bookingEntityPage.getNumberOfElements());
        assertEquals(5, bookingEntityPage.getSize());
        assertEquals(bookingEntity1, bookingEntityPage.getContent().get(0));

        assertThrows(IllegalArgumentException.class, () -> bookingRepository.findByPassengerId(52L, PageRequest.of(0, 0)));
        assertThrows(IllegalArgumentException.class, () -> bookingRepository.findByPassengerId(52L, PageRequest.of(-1, 0)));
        assertThrows(IllegalArgumentException.class, () -> bookingRepository.findByPassengerId(52L, PageRequest.of(-1, 10)));
    }

}