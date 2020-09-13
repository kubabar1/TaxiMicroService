package com.taximicroservice.bookingservice.service.impl;

import com.taximicroservice.bookingservice.exception.BookingServiceException;
import com.taximicroservice.bookingservice.model.dto.BookingResponseDTO;
import com.taximicroservice.bookingservice.model.utils.BookingStatusEnum;
import com.taximicroservice.bookingservice.service.BookingHistoryService;
import com.taximicroservice.bookingservice.utils.BookingResponseUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class BookingHistoryServiceImplTest {

    @Autowired
    private BookingHistoryService bookingHistoryService;

    private static BookingResponseDTO bookingResponseAssigned1;

    private static BookingResponseDTO bookingResponseAssigned2;


    @BeforeAll
    static void initBeforeAll() {
        bookingResponseAssigned1 = new BookingResponseUtils.Builder()
                .setId(2L)
                .setPassengerId(2L)
                .setDriverId(52L)
                .setBookingStatus(BookingStatusEnum.ASSIGNED)
                .setCreationDate("2020-08-21 07:02:12")
                .setStartPoint(20.998274, 52.251428)
                .setFinishPoint(21.104665, 52.246442)
                .build();
        bookingResponseAssigned2 = new BookingResponseUtils.Builder()
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
    void getDriverBookingsHistoryPage() {
        Page<BookingResponseDTO> bookingResponseDTOPage = bookingHistoryService.getDriverBookingsHistoryPage(52L, 0, 5);

        assertEquals(1, bookingResponseDTOPage.getTotalPages());
        assertEquals(2, bookingResponseDTOPage.getTotalElements());
        assertEquals(0, bookingResponseDTOPage.getNumber());
        assertEquals(2, bookingResponseDTOPage.getNumberOfElements());
        assertEquals(5, bookingResponseDTOPage.getSize());
        assertEquals(bookingResponseAssigned1, bookingResponseDTOPage.getContent().get(0));
        assertEquals(bookingResponseAssigned2, bookingResponseDTOPage.getContent().get(1));

        assertThrows(BookingServiceException.class, () -> bookingHistoryService.getDriverBookingsHistoryPage(52L, 0, 0));
        assertThrows(BookingServiceException.class, () -> bookingHistoryService.getDriverBookingsHistoryPage(52L, -1, 0));
        assertThrows(BookingServiceException.class, () -> bookingHistoryService.getDriverBookingsHistoryPage(52L, -1, 10));
    }

    @Test
    void getPassengerBookingsHistoryPage() {
        Page<BookingResponseDTO> bookingResponseDTOPage = bookingHistoryService.getPassengerBookingsHistoryPage(2L, 0, 5);

        assertEquals(1, bookingResponseDTOPage.getTotalPages());
        assertEquals(1, bookingResponseDTOPage.getTotalElements());
        assertEquals(0, bookingResponseDTOPage.getNumber());
        assertEquals(1, bookingResponseDTOPage.getNumberOfElements());
        assertEquals(5, bookingResponseDTOPage.getSize());
        assertEquals(bookingResponseAssigned1, bookingResponseDTOPage.getContent().get(0));

        assertThrows(BookingServiceException.class, () -> bookingHistoryService.getPassengerBookingsHistoryPage(2L, 0, 0));
        assertThrows(BookingServiceException.class, () -> bookingHistoryService.getPassengerBookingsHistoryPage(2L, -1, 0));
        assertThrows(BookingServiceException.class, () -> bookingHistoryService.getPassengerBookingsHistoryPage(2L, -1, 10));
    }

}