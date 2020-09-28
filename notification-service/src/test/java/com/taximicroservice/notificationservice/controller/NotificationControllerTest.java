package com.taximicroservice.notificationservice.controller;

import com.taximicroservice.notificationservice.exception.NotificationServiceException;
import com.taximicroservice.notificationservice.model.dto.NotificationResponseDTO;
import com.taximicroservice.notificationservice.service.NotificationService;
import com.taximicroservice.notificationservice.utils.NotificationTestUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = NotificationController.class)
class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificationService notificationServiceMock;


    @Test
    void getNotifications() throws Exception {
        List<NotificationResponseDTO> notificationResponseDTOTestList = new ArrayList<>();
        notificationResponseDTOTestList.add(NotificationTestUtil.getNotificationResponseDTO1());
        notificationResponseDTOTestList.add(NotificationTestUtil.getNotificationResponseDTO1());

        when(notificationServiceMock.getNotifications(0, 2)).thenReturn(new PageImpl<>(notificationResponseDTOTestList));
        when(notificationServiceMock.getNotifications(0, 0)).thenThrow(NotificationServiceException.class);
        when(notificationServiceMock.getNotifications(-1, 0)).thenThrow(NotificationServiceException.class);
        when(notificationServiceMock.getNotifications(-1, 10)).thenThrow(NotificationServiceException.class);


        mockMvc.perform(get("/notifications").param("page", "0").param("count", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size").value(2))
                .andExpect(jsonPath("$.number").value(0))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.totalElements").value(2))
                .andExpect(jsonPath("$.empty").value(false))
                .andExpect(jsonPath("$.first").value(true))
                .andExpect(jsonPath("$.content[0].id").value(1L))
                .andExpect(jsonPath("$.content[0].type").value("INFO"))
                .andExpect(jsonPath("$.content[0].status").value("SENT"))
                .andExpect(jsonPath("$.content[0].content").value("Driver Bruce Wayne assigned to your booking"))
                .andExpect(jsonPath("$.content[0].sender").value("booking-service"))
                .andExpect(jsonPath("$.content[0].receiverUsername").value("adam123"))
                .andExpect(jsonPath("$.content[0].creationDate").value("2020-09-14 09:01:44"));


        mockMvc.perform(get("/notifications").param("page", "0").param("count", "0"))
                .andExpect(status().isUnprocessableEntity());
        mockMvc.perform(get("/notifications").param("page", "-1").param("count", "0"))
                .andExpect(status().isUnprocessableEntity());
        mockMvc.perform(get("/notifications").param("page", "-1").param("count", "10"))
                .andExpect(status().isUnprocessableEntity());


        when(notificationServiceMock.getNotifications(0, 2)).thenReturn(new PageImpl<>(new ArrayList<>()));
        mockMvc.perform(get("/notifications").param("page", "0").param("count", "2"))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.size").value(0))
                .andExpect(jsonPath("$.number").value(0))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.totalElements").value(0))
                .andExpect(jsonPath("$.empty").value(true))
                .andExpect(jsonPath("$.first").value(true));
    }

    @Test
    void getNotificationById() throws Exception {
        List<NotificationResponseDTO> notificationResponseDTOTestList = new ArrayList<>();
        notificationResponseDTOTestList.add(NotificationTestUtil.getNotificationResponseDTO1());
        notificationResponseDTOTestList.add(NotificationTestUtil.getNotificationResponseDTO1());

        when(notificationServiceMock.getNotificationById(1L)).thenReturn(NotificationTestUtil.getNotificationResponseDTO1());
        when(notificationServiceMock.getNotificationById(111L)).thenThrow(new EntityNotFoundException());

        mockMvc.perform(get("/notifications/{notificationId}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.type").value("INFO"))
                .andExpect(jsonPath("$.status").value("SENT"))
                .andExpect(jsonPath("$.content").value("Driver Bruce Wayne assigned to your booking"))
                .andExpect(jsonPath("$.sender").value("booking-service"))
                .andExpect(jsonPath("$.receiverUsername").value("adam123"))
                .andExpect(jsonPath("$.creationDate").value("2020-09-14 09:01:44"));

        mockMvc.perform(get("/notifications/{notificationId}", 111L))
                .andExpect(status().isNotFound());
    }

    @Test
    void getNotificationsByUserId() throws Exception {
        List<NotificationResponseDTO> notificationResponseDTOTestList = new ArrayList<>();
        notificationResponseDTOTestList.add(NotificationTestUtil.getNotificationResponseDTO1());
        notificationResponseDTOTestList.add(NotificationTestUtil.getNotificationResponseDTO1());

        when(notificationServiceMock.getNotificationsByReceiverUsername("adam123", 0, 2)).thenReturn(new PageImpl<>(notificationResponseDTOTestList));
        when(notificationServiceMock.getNotificationsByReceiverUsername("adam123", 0, 0)).thenThrow(NotificationServiceException.class);
        when(notificationServiceMock.getNotificationsByReceiverUsername("adam123", -1, 0)).thenThrow(NotificationServiceException.class);
        when(notificationServiceMock.getNotificationsByReceiverUsername("adam123", -1, 10)).thenThrow(NotificationServiceException.class);


        mockMvc.perform(get("/notifications/username/{userId}", "adam123").param("page", "0").param("count", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size").value(2))
                .andExpect(jsonPath("$.number").value(0))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.totalElements").value(2))
                .andExpect(jsonPath("$.empty").value(false))
                .andExpect(jsonPath("$.first").value(true))
                .andExpect(jsonPath("$.content[0].id").value(1L))
                .andExpect(jsonPath("$.content[0].type").value("INFO"))
                .andExpect(jsonPath("$.content[0].status").value("SENT"))
                .andExpect(jsonPath("$.content[0].content").value("Driver Bruce Wayne assigned to your booking"))
                .andExpect(jsonPath("$.content[0].sender").value("booking-service"))
                .andExpect(jsonPath("$.content[0].receiverUsername").value("adam123"))
                .andExpect(jsonPath("$.content[0].creationDate").value("2020-09-14 09:01:44"));


        mockMvc.perform(get("/notifications/username/{userId}", "adam123").param("page", "0").param("count", "0"))
                .andExpect(status().isUnprocessableEntity());
        mockMvc.perform(get("/notifications/username/{userId}", "adam123").param("page", "-1").param("count", "0"))
                .andExpect(status().isUnprocessableEntity());
        mockMvc.perform(get("/notifications/username/{userId}", "adam123").param("page", "-1").param("count", "10"))
                .andExpect(status().isUnprocessableEntity());


        when(notificationServiceMock.getNotificationsByReceiverUsername("adam123", 0, 2)).thenReturn(new PageImpl<>(new ArrayList<>()));
        mockMvc.perform(get("/notifications/username/{userId}", "adam123").param("page", "0").param("count", "2"))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.size").value(0))
                .andExpect(jsonPath("$.number").value(0))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.totalElements").value(0))
                .andExpect(jsonPath("$.empty").value(true))
                .andExpect(jsonPath("$.first").value(true));
    }

}