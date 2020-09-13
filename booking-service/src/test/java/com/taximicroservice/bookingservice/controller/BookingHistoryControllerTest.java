package com.taximicroservice.bookingservice.controller;

import com.taximicroservice.bookingservice.exception.BookingServiceException;
import com.taximicroservice.bookingservice.model.dto.BookingResponseDTO;
import com.taximicroservice.bookingservice.model.utils.BookingStatusEnum;
import com.taximicroservice.bookingservice.service.BookingHistoryService;
import com.taximicroservice.bookingservice.utils.BookingResponseUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BookingHistoryController.class)
class BookingHistoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingHistoryService bookingHistoryService;

    private static BookingResponseDTO bookingResponseAssigned1;

    private static BookingResponseDTO bookingResponseAssigned2;


    @BeforeAll
    static void initBeforeAll() {
        bookingResponseAssigned1 = new BookingResponseUtils.Builder()
                .setId(1L)
                .setPassengerId(2L)
                .setDriverId(52L)
                .setBookingStatus(BookingStatusEnum.ASSIGNED)
                .setCreationDate("2020-08-21 07:02:12")
                .setStartPoint(20.998274, 52.251428)
                .setFinishPoint(21.104665, 52.246442)
                .build();
        bookingResponseAssigned2 = new BookingResponseUtils.Builder()
                .setId(2L)
                .setPassengerId(12L)
                .setDriverId(52L)
                .setBookingStatus(BookingStatusEnum.ASSIGNED)
                .setCreationDate("2020-08-21 07:02:12")
                .setStartPoint(21.027313, 52.248855)
                .setFinishPoint(21.102365, 52.246422)
                .build();
    }


    @Test
    void getDriverBookingsHistoryPage() throws Exception {
        List<BookingResponseDTO> bookingResponseDTOList = new ArrayList<>();
        bookingResponseDTOList.add(bookingResponseAssigned1);
        bookingResponseDTOList.add(bookingResponseAssigned2);

        when(bookingHistoryService.getDriverBookingsHistoryPage(52L, 0, 2)).thenReturn(new PageImpl<>(bookingResponseDTOList));
        mockMvc.perform(get("/bookings/history/driver/52").param("page", "0").param("count", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size").value(2))
                .andExpect(jsonPath("$.number").value(0))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.totalElements").value(2))
                .andExpect(jsonPath("$.empty").value(false))
                .andExpect(jsonPath("$.first").value(true))
                .andExpect(jsonPath("$.content[0].id").value(1L))
                .andExpect(jsonPath("$.content[0].passengerId").value(2L))
                .andExpect(jsonPath("$.content[0].driverId").value(52L))
                .andExpect(jsonPath("$.content[0].startPoint.type").value("Point"))
                .andExpect(jsonPath("$.content[0].startPoint.coordinates[0]").value(20.998274))
                .andExpect(jsonPath("$.content[0].startPoint.coordinates[1]").value(52.251428))
                .andExpect(jsonPath("$.content[0].finishPoint.type").value("Point"))
                .andExpect(jsonPath("$.content[0].finishPoint.coordinates[0]").value(21.104665))
                .andExpect(jsonPath("$.content[0].finishPoint.coordinates[1]").value(52.246442))
                .andExpect(jsonPath("$.content[0].creationDate").value("2020-08-21 07:02:12.000000"))
                .andExpect(jsonPath("$.content[0].status.id").value("ASSIGNED"))
                .andExpect(jsonPath("$.content[0].status.description").value("Booking assigned to driver"))
                .andExpect(jsonPath("$.content[1].id").value(2L))
                .andExpect(jsonPath("$.content[1].passengerId").value(12L))
                .andExpect(jsonPath("$.content[1].driverId").value(52L))
                .andExpect(jsonPath("$.content[1].startPoint.type").value("Point"))
                .andExpect(jsonPath("$.content[1].startPoint.coordinates[0]").value(21.027313))
                .andExpect(jsonPath("$.content[1].startPoint.coordinates[1]").value(52.248855))
                .andExpect(jsonPath("$.content[1].finishPoint.type").value("Point"))
                .andExpect(jsonPath("$.content[1].finishPoint.coordinates[0]").value(21.102365))
                .andExpect(jsonPath("$.content[1].finishPoint.coordinates[1]").value(52.246422))
                .andExpect(jsonPath("$.content[1].creationDate").value("2020-08-21 07:02:12.000000"))
                .andExpect(jsonPath("$.content[1].status.id").value("ASSIGNED"))
                .andExpect(jsonPath("$.content[1].status.description").value("Booking assigned to driver"));

        when(bookingHistoryService.getDriverBookingsHistoryPage(52L, 0, 0)).thenThrow(BookingServiceException.class);
        mockMvc.perform(get("/bookings/history/driver/52").param("page", "0").param("count", "0"))
                .andExpect(status().isUnprocessableEntity());

        when(bookingHistoryService.getDriverBookingsHistoryPage(52L, 0, 2)).thenReturn(new PageImpl<>(new ArrayList<>()));
        mockMvc.perform(get("/bookings/history/driver/52").param("page", "0").param("count", "2"))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.size").value(0))
                .andExpect(jsonPath("$.number").value(0))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.totalElements").value(0))
                .andExpect(jsonPath("$.empty").value(true))
                .andExpect(jsonPath("$.first").value(true));
    }

    @Test
    void getPassengerBookingsHistoryPage() throws Exception {
        when(bookingHistoryService.getPassengerBookingsHistoryPage(2L, 0, 2)).thenReturn(new PageImpl<>(Collections.singletonList(bookingResponseAssigned1)));
        mockMvc.perform(get("/bookings/history/passenger/2").param("page", "0").param("count", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size").value(1))
                .andExpect(jsonPath("$.number").value(0))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.totalElements").value(1))
                .andExpect(jsonPath("$.empty").value(false))
                .andExpect(jsonPath("$.first").value(true))
                .andExpect(jsonPath("$.content[0].id").value(1L))
                .andExpect(jsonPath("$.content[0].passengerId").value(2L))
                .andExpect(jsonPath("$.content[0].driverId").value(52L))
                .andExpect(jsonPath("$.content[0].startPoint.type").value("Point"))
                .andExpect(jsonPath("$.content[0].startPoint.coordinates[0]").value(20.998274))
                .andExpect(jsonPath("$.content[0].startPoint.coordinates[1]").value(52.251428))
                .andExpect(jsonPath("$.content[0].finishPoint.type").value("Point"))
                .andExpect(jsonPath("$.content[0].finishPoint.coordinates[0]").value(21.104665))
                .andExpect(jsonPath("$.content[0].finishPoint.coordinates[1]").value(52.246442))
                .andExpect(jsonPath("$.content[0].creationDate").value("2020-08-21 07:02:12.000000"))
                .andExpect(jsonPath("$.content[0].status.id").value("ASSIGNED"))
                .andExpect(jsonPath("$.content[0].status.description").value("Booking assigned to driver"));

        when(bookingHistoryService.getPassengerBookingsHistoryPage(2L, 0, 0)).thenThrow(BookingServiceException.class);
        mockMvc.perform(get("/bookings/history/passenger/2").param("page", "0").param("count", "0"))
                .andExpect(status().isUnprocessableEntity());

        when(bookingHistoryService.getPassengerBookingsHistoryPage(2L, 0, 2)).thenReturn(new PageImpl<>(new ArrayList<>()));
        mockMvc.perform(get("/bookings/history/passenger/2").param("page", "0").param("count", "2"))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.size").value(0))
                .andExpect(jsonPath("$.number").value(0))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.totalElements").value(0))
                .andExpect(jsonPath("$.empty").value(true))
                .andExpect(jsonPath("$.first").value(true));
    }

}