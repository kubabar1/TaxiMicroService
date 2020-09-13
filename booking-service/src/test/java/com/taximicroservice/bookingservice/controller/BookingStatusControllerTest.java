package com.taximicroservice.bookingservice.controller;

import com.taximicroservice.bookingservice.exception.BookingServiceException;
import com.taximicroservice.bookingservice.model.dto.BookingResponseDTO;
import com.taximicroservice.bookingservice.model.utils.BookingStatusEnum;
import com.taximicroservice.bookingservice.service.BookingStatusService;
import com.taximicroservice.bookingservice.utils.BookingResponseUtils;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityNotFoundException;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BookingStatusController.class)
class BookingStatusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingStatusService bookingStatusServiceMock;

    private static BookingResponseDTO bookingAfterAssigned;

    private static BookingResponseDTO bookingAfterAbort;

    private static BookingResponseDTO bookingAfterCancel;

    private static BookingResponseDTO bookingAfterFinish;

    private static BookingResponseDTO bookingAfterStart;

    private static BookingResponseDTO bookingAfterUnassign;


    @BeforeAll
    static void initBeforeAll() {
        bookingAfterAssigned = new BookingResponseUtils.Builder()
                .setId(1L)
                .setPassengerId(2L)
                .setDriverId(52L)
                .setBookingStatus(BookingStatusEnum.ASSIGNED)
                .setCreationDate("2020-08-21 07:02:12")
                .setStartPoint(20.998274, 52.251428)
                .setFinishPoint(21.104665, 52.246442)
                .build();
        bookingAfterAbort = new BookingResponseUtils.Builder()
                .setId(1L)
                .setPassengerId(2L)
                .setDriverId(52L)
                .setBookingStatus(BookingStatusEnum.ABORTED)
                .setCreationDate("2020-08-21 07:02:12")
                .setStartPoint(20.998274, 52.251428)
                .setFinishPoint(21.104665, 52.246442)
                .build();
        bookingAfterCancel = new BookingResponseUtils.Builder()
                .setId(1L)
                .setPassengerId(2L)
                .setDriverId(52L)
                .setBookingStatus(BookingStatusEnum.CANCELED)
                .setCreationDate("2020-08-21 07:02:12")
                .setStartPoint(20.998274, 52.251428)
                .setFinishPoint(21.104665, 52.246442)
                .build();
        bookingAfterFinish = new BookingResponseUtils.Builder()
                .setId(1L)
                .setPassengerId(2L)
                .setDriverId(52L)
                .setBookingStatus(BookingStatusEnum.FINISHED)
                .setCreationDate("2020-08-21 07:02:12")
                .setStartPoint(20.998274, 52.251428)
                .setFinishPoint(21.104665, 52.246442)
                .build();
        bookingAfterStart = new BookingResponseUtils.Builder()
                .setId(1L)
                .setPassengerId(2L)
                .setDriverId(52L)
                .setBookingStatus(BookingStatusEnum.IN_PROGRESS)
                .setCreationDate("2020-08-21 07:02:12")
                .setStartPoint(20.998274, 52.251428)
                .setFinishPoint(21.104665, 52.246442)
                .build();
        bookingAfterUnassign = new BookingResponseUtils.Builder()
                .setId(1L)
                .setPassengerId(2L)
                .setDriverId(null)
                .setBookingStatus(BookingStatusEnum.CREATED)
                .setCreationDate("2020-08-21 07:02:12")
                .setStartPoint(20.998274, 52.251428)
                .setFinishPoint(21.104665, 52.246442)
                .build();
    }

    @Test
    void assignDriverToBooking() throws Exception {
        when(bookingStatusServiceMock.assignDriverToBooking(52L, 2L)).thenReturn(bookingAfterAssigned);
        mockMvc.perform(put("/bookings/status/assign-driver/2").param("driverId", "52"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.passengerId").value(2L))
                .andExpect(jsonPath("$.driverId").value(52L))
                .andExpect(jsonPath("$.startPoint.type").value("Point"))
                .andExpect(jsonPath("$.startPoint.coordinates[0]").value(20.998274))
                .andExpect(jsonPath("$.startPoint.coordinates[1]").value(52.251428))
                .andExpect(jsonPath("$.finishPoint.type").value("Point"))
                .andExpect(jsonPath("$.finishPoint.coordinates[0]").value(21.104665))
                .andExpect(jsonPath("$.finishPoint.coordinates[1]").value(52.246442))
                .andExpect(jsonPath("$.creationDate").value("2020-08-21 07:02:12.000000"))
                .andExpect(jsonPath("$.status.id").value("ASSIGNED"))
                .andExpect(jsonPath("$.status.description").value("Booking assigned to driver"));


        when(bookingStatusServiceMock.assignDriverToBooking(52L, 100L)).thenThrow(EntityNotFoundException.class);
        mockMvc.perform(put("/bookings/status/assign-driver/100").param("driverId", "52"))
                .andExpect(status().isNotFound());


        when(bookingStatusServiceMock.assignDriverToBooking(52L, 5L)).thenThrow(BookingServiceException.class);
        mockMvc.perform(put("/bookings/status/assign-driver/5").param("driverId", "52"))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void abortBooking() throws Exception {
        when(bookingStatusServiceMock.abortBooking(2L)).thenReturn(bookingAfterAbort);
        mockMvc.perform(put("/bookings/status/abort/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.passengerId").value(2L))
                .andExpect(jsonPath("$.driverId").value(52L))
                .andExpect(jsonPath("$.startPoint.type").value("Point"))
                .andExpect(jsonPath("$.startPoint.coordinates[0]").value(20.998274))
                .andExpect(jsonPath("$.startPoint.coordinates[1]").value(52.251428))
                .andExpect(jsonPath("$.finishPoint.type").value("Point"))
                .andExpect(jsonPath("$.finishPoint.coordinates[0]").value(21.104665))
                .andExpect(jsonPath("$.finishPoint.coordinates[1]").value(52.246442))
                .andExpect(jsonPath("$.creationDate").value("2020-08-21 07:02:12.000000"))
                .andExpect(jsonPath("$.status.id").value("ABORTED"))
                .andExpect(jsonPath("$.status.description").value("Booking aborted"));


        when(bookingStatusServiceMock.abortBooking(100L)).thenThrow(EntityNotFoundException.class);
        mockMvc.perform(put("/bookings/status/abort/100"))
                .andExpect(status().isNotFound());


        when(bookingStatusServiceMock.abortBooking(5L)).thenThrow(BookingServiceException.class);
        mockMvc.perform(put("/bookings/status/abort/5"))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void cancelBooking() throws Exception {
        when(bookingStatusServiceMock.cancelBooking(2L)).thenReturn(bookingAfterCancel);
        mockMvc.perform(put("/bookings/status/cancel/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.passengerId").value(2L))
                .andExpect(jsonPath("$.driverId").value(52L))
                .andExpect(jsonPath("$.startPoint.type").value("Point"))
                .andExpect(jsonPath("$.startPoint.coordinates[0]").value(20.998274))
                .andExpect(jsonPath("$.startPoint.coordinates[1]").value(52.251428))
                .andExpect(jsonPath("$.finishPoint.type").value("Point"))
                .andExpect(jsonPath("$.finishPoint.coordinates[0]").value(21.104665))
                .andExpect(jsonPath("$.finishPoint.coordinates[1]").value(52.246442))
                .andExpect(jsonPath("$.creationDate").value("2020-08-21 07:02:12.000000"))
                .andExpect(jsonPath("$.status.id").value("CANCELED"))
                .andExpect(jsonPath("$.status.description").value("Booking canceled"));


        when(bookingStatusServiceMock.cancelBooking(100L)).thenThrow(EntityNotFoundException.class);
        mockMvc.perform(put("/bookings/status/cancel/100"))
                .andExpect(status().isNotFound());


        when(bookingStatusServiceMock.cancelBooking(5L)).thenThrow(BookingServiceException.class);
        mockMvc.perform(put("/bookings/status/cancel/5"))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void finishBooking() throws Exception {
        when(bookingStatusServiceMock.finishBooking(2L)).thenReturn(bookingAfterFinish);
        mockMvc.perform(put("/bookings/status/finish/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.passengerId").value(2L))
                .andExpect(jsonPath("$.driverId").value(52L))
                .andExpect(jsonPath("$.startPoint.type").value("Point"))
                .andExpect(jsonPath("$.startPoint.coordinates[0]").value(20.998274))
                .andExpect(jsonPath("$.startPoint.coordinates[1]").value(52.251428))
                .andExpect(jsonPath("$.finishPoint.type").value("Point"))
                .andExpect(jsonPath("$.finishPoint.coordinates[0]").value(21.104665))
                .andExpect(jsonPath("$.finishPoint.coordinates[1]").value(52.246442))
                .andExpect(jsonPath("$.creationDate").value("2020-08-21 07:02:12.000000"))
                .andExpect(jsonPath("$.status.id").value("FINISHED"))
                .andExpect(jsonPath("$.status.description").value("Booking finished"));


        when(bookingStatusServiceMock.finishBooking(100L)).thenThrow(EntityNotFoundException.class);
        mockMvc.perform(put("/bookings/status/finish/100"))
                .andExpect(status().isNotFound());


        when(bookingStatusServiceMock.finishBooking(5L)).thenThrow(BookingServiceException.class);
        mockMvc.perform(put("/bookings/status/finish/5"))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void startBookingProgress() throws Exception {
        when(bookingStatusServiceMock.startBookingProgress(2L)).thenReturn(bookingAfterStart);
        mockMvc.perform(put("/bookings/status/start/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.passengerId").value(2L))
                .andExpect(jsonPath("$.driverId").value(52L))
                .andExpect(jsonPath("$.startPoint.type").value("Point"))
                .andExpect(jsonPath("$.startPoint.coordinates[0]").value(20.998274))
                .andExpect(jsonPath("$.startPoint.coordinates[1]").value(52.251428))
                .andExpect(jsonPath("$.finishPoint.type").value("Point"))
                .andExpect(jsonPath("$.finishPoint.coordinates[0]").value(21.104665))
                .andExpect(jsonPath("$.finishPoint.coordinates[1]").value(52.246442))
                .andExpect(jsonPath("$.creationDate").value("2020-08-21 07:02:12.000000"))
                .andExpect(jsonPath("$.status.id").value("IN_PROGRESS"))
                .andExpect(jsonPath("$.status.description").value("Booking in progress"));


        when(bookingStatusServiceMock.startBookingProgress(100L)).thenThrow(EntityNotFoundException.class);
        mockMvc.perform(put("/bookings/status/start/100"))
                .andExpect(status().isNotFound());


        when(bookingStatusServiceMock.startBookingProgress(5L)).thenThrow(BookingServiceException.class);
        mockMvc.perform(put("/bookings/status/start/5"))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void unassignBooking() throws Exception {
        when(bookingStatusServiceMock.unassignDriverFromBooking(2L)).thenReturn(bookingAfterUnassign);
        mockMvc.perform(put("/bookings/status/unassign-driver/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.passengerId").value(2L))
                .andExpect(jsonPath("$.driverId").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.startPoint.type").value("Point"))
                .andExpect(jsonPath("$.startPoint.coordinates[0]").value(20.998274))
                .andExpect(jsonPath("$.startPoint.coordinates[1]").value(52.251428))
                .andExpect(jsonPath("$.finishPoint.type").value("Point"))
                .andExpect(jsonPath("$.finishPoint.coordinates[0]").value(21.104665))
                .andExpect(jsonPath("$.finishPoint.coordinates[1]").value(52.246442))
                .andExpect(jsonPath("$.creationDate").value("2020-08-21 07:02:12.000000"))
                .andExpect(jsonPath("$.status.id").value("CREATED"))
                .andExpect(jsonPath("$.status.description").value("Booking created"));


        when(bookingStatusServiceMock.unassignDriverFromBooking(100L)).thenThrow(EntityNotFoundException.class);
        mockMvc.perform(put("/bookings/status/unassign-driver/100"))
                .andExpect(status().isNotFound());


        when(bookingStatusServiceMock.unassignDriverFromBooking(5L)).thenThrow(BookingServiceException.class);
        mockMvc.perform(put("/bookings/status/unassign-driver/5"))
                .andExpect(status().isUnprocessableEntity());
    }

}