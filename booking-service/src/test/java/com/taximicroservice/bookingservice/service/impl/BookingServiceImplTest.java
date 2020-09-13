package com.taximicroservice.bookingservice.service.impl;

import com.taximicroservice.bookingservice.exception.BookingServiceException;
import com.taximicroservice.bookingservice.model.dto.BookingResponseDTO;
import com.taximicroservice.bookingservice.model.utils.BookingStatusEnum;
import com.taximicroservice.bookingservice.service.BookingService;
import com.taximicroservice.bookingservice.utils.BookingResponseUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.DirtiesContext;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class BookingServiceImplTest {

    @Autowired
    private BookingService bookingService;

    private static BookingResponseDTO bookingResponseAssigned1;

    private static BookingResponseDTO bookingResponseAssignedTo52Driver1;

    private static BookingResponseDTO bookingResponseAssignedTo52Driver2;


    @BeforeAll
    static void initBeforeAll() {
        bookingResponseAssigned1 = new BookingResponseUtils.Builder()
                .setId(1L)
                .setPassengerId(1L)
                .setDriverId(51L)
                .setBookingStatus(BookingStatusEnum.ASSIGNED)
                .setCreationDate("2020-08-20 06:01:11")
                .setStartPoint(20.989324, 52.246456)
                .setFinishPoint(21.096957, 52.192845)
                .build();
        bookingResponseAssignedTo52Driver1 = new BookingResponseUtils.Builder()
                .setId(2L)
                .setPassengerId(2L)
                .setDriverId(52L)
                .setBookingStatus(BookingStatusEnum.ASSIGNED)
                .setCreationDate("2020-08-21 07:02:12")
                .setStartPoint(20.998274, 52.251428)
                .setFinishPoint(21.104665, 52.246442)
                .build();
        bookingResponseAssignedTo52Driver2 = new BookingResponseUtils.Builder()
                .setId(3L)
                .setPassengerId(12L)
                .setDriverId(52L)
                .setBookingStatus(BookingStatusEnum.ASSIGNED)
                .setCreationDate("2020-08-21 07:02:12")
                .setStartPoint(21.027313, 52.248855)
                .setFinishPoint(21.102365, 52.246422)
                .build();
    }

    @Test
    void getBookingsPage() {
        Page<BookingResponseDTO> bookingResponseDTOPage = bookingService.getBookingsPage(0, 5);

        assertEquals(2, bookingResponseDTOPage.getTotalPages());
        assertEquals(9, bookingResponseDTOPage.getTotalElements());
        assertEquals(0, bookingResponseDTOPage.getNumber());
        assertEquals(5, bookingResponseDTOPage.getNumberOfElements());
        assertEquals(5, bookingResponseDTOPage.getSize());
        assertEquals(bookingResponseAssigned1, bookingResponseDTOPage.getContent().get(0));

        assertThrows(BookingServiceException.class, () -> bookingService.getBookingsPage(0, 0));
        assertThrows(BookingServiceException.class, () -> bookingService.getBookingsPage(-1, 0));
        assertThrows(BookingServiceException.class, () -> bookingService.getBookingsPage(-1, 10));
    }

    @Test
    void getBookingById() {
        BookingResponseDTO bookingResponseDTO = bookingService.getBookingById(1L);
        assertEquals(BookingServiceImplTest.bookingResponseAssigned1, bookingResponseDTO);
        assertThrows(EntityNotFoundException.class, () -> bookingService.getBookingById(100L));
    }

    @Test
    void getBookingsAssignedToDriver() {
        Page<BookingResponseDTO> bookingResponseDTOPage = bookingService.getBookingsAssignedToDriver(52L, 0, 5);

        assertEquals(1, bookingResponseDTOPage.getTotalPages());
        assertEquals(2, bookingResponseDTOPage.getTotalElements());
        assertEquals(0, bookingResponseDTOPage.getNumber());
        assertEquals(2, bookingResponseDTOPage.getNumberOfElements());
        assertEquals(5, bookingResponseDTOPage.getSize());
        assertEquals(bookingResponseAssignedTo52Driver1, bookingResponseDTOPage.getContent().get(0));
        assertEquals(bookingResponseAssignedTo52Driver2, bookingResponseDTOPage.getContent().get(1));

        assertThrows(BookingServiceException.class, () -> bookingService.getBookingsPage(0, 0));
        assertThrows(BookingServiceException.class, () -> bookingService.getBookingsPage(-1, 0));
        assertThrows(BookingServiceException.class, () -> bookingService.getBookingsPage(-1, 10));
    }

    void getNearbyCreatedBookings() {
        // TODO: add integration test
    }

    void addBooking() {
        // TODO: add integration test
    }

}