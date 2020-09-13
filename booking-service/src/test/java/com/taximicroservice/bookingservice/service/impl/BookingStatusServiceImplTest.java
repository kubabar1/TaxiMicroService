package com.taximicroservice.bookingservice.service.impl;

import com.taximicroservice.bookingservice.exception.BookingServiceException;
import com.taximicroservice.bookingservice.model.dto.BookingResponseDTO;
import com.taximicroservice.bookingservice.model.utils.BookingStatusEnum;
import com.taximicroservice.bookingservice.service.BookingService;
import com.taximicroservice.bookingservice.service.BookingStatusService;
import com.taximicroservice.bookingservice.utils.BookingResponseUtils;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class BookingStatusServiceImplTest {

    @Autowired
    private BookingStatusService bookingStatusService;

    @Autowired
    private BookingService bookingService;

    private static BookingResponseDTO bookingBeforeAssign;

    private static BookingResponseDTO bookingAfterAssign;

    private static BookingResponseDTO bookingBeforeStart;

    private static BookingResponseDTO bookingAfterStart;

    private static BookingResponseDTO bookingBeforeFinish;

    private static BookingResponseDTO bookingAfterFinish;

    private static BookingResponseDTO bookingBeforeAbort;

    private static BookingResponseDTO bookingAfterAbort;

    private static BookingResponseDTO bookingBeforeUnassign;

    private static BookingResponseDTO bookingAfterUnassign;

    private static BookingResponseDTO bookingBeforeCancel;

    private static BookingResponseDTO bookingAfterCancel;


    @BeforeAll
    static void initBeforeAll() {
        bookingBeforeAssign = new BookingResponseUtils.Builder()
                .setId(8L)
                .setPassengerId(7L)
                .setDriverId(null)
                .setBookingStatus(BookingStatusEnum.CREATED)
                .setCreationDate("2020-08-26 12:07:17")
                .setStartPoint(21.066712, 52.184786)
                .setFinishPoint(21.032565, 52.157512)
                .build();
        bookingAfterAssign = new BookingResponseUtils.Builder()
                .setId(8L)
                .setPassengerId(7L)
                .setDriverId(51L)
                .setBookingStatus(BookingStatusEnum.ASSIGNED)
                .setCreationDate("2020-08-26 12:07:17")
                .setStartPoint(21.066712, 52.184786)
                .setFinishPoint(21.032565, 52.157512)
                .build();
        bookingBeforeStart = new BookingResponseUtils.Builder()
                .setId(3L)
                .setPassengerId(12L)
                .setDriverId(52L)
                .setBookingStatus(BookingStatusEnum.ASSIGNED)
                .setCreationDate("2020-08-21 07:02:12")
                .setStartPoint(21.027313, 52.248855)
                .setFinishPoint(21.102365, 52.246422)
                .build();
        bookingAfterStart = new BookingResponseUtils.Builder()
                .setId(3L)
                .setPassengerId(12L)
                .setDriverId(52L)
                .setBookingStatus(BookingStatusEnum.IN_PROGRESS)
                .setCreationDate("2020-08-21 07:02:12")
                .setStartPoint(21.027313, 52.248855)
                .setFinishPoint(21.102365, 52.246422)
                .build();
        bookingBeforeFinish = new BookingResponseUtils.Builder()
                .setId(4L)
                .setPassengerId(3L)
                .setDriverId(53L)
                .setBookingStatus(BookingStatusEnum.IN_PROGRESS)
                .setCreationDate("2020-08-22 08:03:13")
                .setStartPoint(21.026713, 52.248911)
                .setFinishPoint(20.947007, 52.353646)
                .build();
        bookingAfterFinish = new BookingResponseUtils.Builder()
                .setId(4L)
                .setPassengerId(3L)
                .setDriverId(53L)
                .setBookingStatus(BookingStatusEnum.FINISHED)
                .setCreationDate("2020-08-22 08:03:13")
                .setStartPoint(21.026713, 52.248911)
                .setFinishPoint(20.947007, 52.353646)
                .build();
        bookingBeforeAbort = new BookingResponseUtils.Builder()
                .setId(9L)
                .setPassengerId(22L)
                .setDriverId(59L)
                .setBookingStatus(BookingStatusEnum.IN_PROGRESS)
                .setCreationDate("2020-08-22 08:03:13")
                .setStartPoint(21.026713, 52.248911)
                .setFinishPoint(20.947007, 52.353646)
                .build();
        bookingAfterAbort = new BookingResponseUtils.Builder()
                .setId(9L)
                .setPassengerId(22L)
                .setDriverId(59L)
                .setBookingStatus(BookingStatusEnum.ABORTED)
                .setCreationDate("2020-08-22 08:03:13")
                .setStartPoint(21.026713, 52.248911)
                .setFinishPoint(20.947007, 52.353646)
                .build();
        bookingBeforeUnassign = new BookingResponseUtils.Builder()
                .setId(2L)
                .setPassengerId(2L)
                .setDriverId(52L)
                .setBookingStatus(BookingStatusEnum.ASSIGNED)
                .setCreationDate("2020-08-21 07:02:12")
                .setStartPoint(20.998274, 52.251428)
                .setFinishPoint(21.104665, 52.246442)
                .build();
        bookingAfterUnassign = new BookingResponseUtils.Builder()
                .setId(2L)
                .setPassengerId(2L)
                .setDriverId(null)
                .setBookingStatus(BookingStatusEnum.CREATED)
                .setCreationDate("2020-08-21 07:02:12")
                .setStartPoint(20.998274, 52.251428)
                .setFinishPoint(21.104665, 52.246442)
                .build();
        bookingBeforeCancel = new BookingResponseUtils.Builder()
                .setId(1L)
                .setPassengerId(1L)
                .setDriverId(51L)
                .setBookingStatus(BookingStatusEnum.ASSIGNED)
                .setCreationDate("2020-08-20 06:01:11")
                .setStartPoint(20.989324, 52.246456)
                .setFinishPoint(21.096957, 52.192845)
                .build();
        bookingAfterCancel = new BookingResponseUtils.Builder()
                .setId(1L)
                .setPassengerId(1L)
                .setDriverId(51L)
                .setBookingStatus(BookingStatusEnum.CANCELED)
                .setCreationDate("2020-08-20 06:01:11")
                .setStartPoint(20.989324, 52.246456)
                .setFinishPoint(21.096957, 52.192845)
                .build();
    }

    @Test
    void assignDriverToBooking() {
        assertEquals(bookingBeforeAssign, bookingService.getBookingById(8L));
        bookingStatusService.assignDriverToBooking(51L, 8L);
        assertEquals(bookingAfterAssign, bookingService.getBookingById(8L));

        assertThrows(BookingServiceException.class, () -> bookingStatusService.assignDriverToBooking(51L, 8L));
        assertThrows(BookingServiceException.class, () -> bookingStatusService.abortBooking(8L));
        // assertThrows(BookingServiceException.class, () -> bookingStatusService.cancelBooking(8L));
        // assertThrows(BookingServiceException.class, () -> bookingStatusService.unassignDriverFromBooking(8L));
        // assertThrows(BookingServiceException.class, () -> bookingStatusService.startBookingProgress( 8L));
        assertThrows(BookingServiceException.class, () -> bookingStatusService.finishBooking(8L));
    }

    @Test
    void startBookingProgress() {
        assertEquals(bookingBeforeStart, bookingService.getBookingById(3L));
        bookingStatusService.startBookingProgress(3L);
        assertEquals(bookingAfterStart, bookingService.getBookingById(3L));

        assertThrows(BookingServiceException.class, () -> bookingStatusService.assignDriverToBooking(51L, 3L));
        assertThrows(BookingServiceException.class, () -> bookingStatusService.cancelBooking(3L));
        assertThrows(BookingServiceException.class, () -> bookingStatusService.unassignDriverFromBooking(3L));
        assertThrows(BookingServiceException.class, () -> bookingStatusService.startBookingProgress(3L));
    }

    @Test
    void finishBooking() {
        assertEquals(bookingBeforeFinish, bookingService.getBookingById(4L));
        bookingStatusService.finishBooking(4L);
        assertEquals(bookingAfterFinish, bookingService.getBookingById(4L));

        assertThrows(BookingServiceException.class, () -> bookingStatusService.assignDriverToBooking(51L, 4L));
        assertThrows(BookingServiceException.class, () -> bookingStatusService.abortBooking(4L));
        assertThrows(BookingServiceException.class, () -> bookingStatusService.cancelBooking(4L));
        assertThrows(BookingServiceException.class, () -> bookingStatusService.unassignDriverFromBooking(4L));
        assertThrows(BookingServiceException.class, () -> bookingStatusService.startBookingProgress(4L));
        assertThrows(BookingServiceException.class, () -> bookingStatusService.finishBooking(4L));
    }

    @Test
    void abortBooking() {
        assertEquals(bookingBeforeAbort, bookingService.getBookingById(9L));
        bookingStatusService.abortBooking(9L);
        assertEquals(bookingAfterAbort, bookingService.getBookingById(9L));

        assertThrows(BookingServiceException.class, () -> bookingStatusService.assignDriverToBooking(51L, 9L));
        assertThrows(BookingServiceException.class, () -> bookingStatusService.abortBooking(9L));
        assertThrows(BookingServiceException.class, () -> bookingStatusService.cancelBooking(9L));
        assertThrows(BookingServiceException.class, () -> bookingStatusService.unassignDriverFromBooking(9L));
        assertThrows(BookingServiceException.class, () -> bookingStatusService.startBookingProgress( 9L));
        assertThrows(BookingServiceException.class, () -> bookingStatusService.finishBooking(9L));
    }

    @Test
    void cancelBooking() {
        assertEquals(bookingBeforeCancel, bookingService.getBookingById(1L));
        bookingStatusService.cancelBooking(1L);
        assertEquals(bookingAfterCancel, bookingService.getBookingById(1L));

        assertThrows(BookingServiceException.class, () -> bookingStatusService.assignDriverToBooking(51L, 1L));
        assertThrows(BookingServiceException.class, () -> bookingStatusService.abortBooking(1L));
        assertThrows(BookingServiceException.class, () -> bookingStatusService.cancelBooking(1L));
        assertThrows(BookingServiceException.class, () -> bookingStatusService.unassignDriverFromBooking(1L));
        assertThrows(BookingServiceException.class, () -> bookingStatusService.startBookingProgress( 1L));
        assertThrows(BookingServiceException.class, () -> bookingStatusService.finishBooking(1L));
    }

    @Test
    void unassignBooking() {
        assertEquals(bookingBeforeUnassign, bookingService.getBookingById(2L));
        bookingStatusService.unassignDriverFromBooking(2L);
        assertEquals(bookingAfterUnassign, bookingService.getBookingById(2L));

        assertThrows(BookingServiceException.class, () -> bookingStatusService.abortBooking(2L));
        assertThrows(BookingServiceException.class, () -> bookingStatusService.unassignDriverFromBooking(2L));
        assertThrows(BookingServiceException.class, () -> bookingStatusService.startBookingProgress( 2L));
        assertThrows(BookingServiceException.class, () -> bookingStatusService.finishBooking(2L));
    }

}