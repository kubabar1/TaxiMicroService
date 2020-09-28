package com.taximicroservice.notificationservice.controller;

import com.taximicroservice.notificationservice.exception.NotificationServiceException;
import com.taximicroservice.notificationservice.model.dto.NotificationResponseDTO;
import com.taximicroservice.notificationservice.model.utils.NotificationStatusEnum;
import com.taximicroservice.notificationservice.service.NotificationService;
import com.taximicroservice.notificationservice.service.NotificationStatusService;
import com.taximicroservice.notificationservice.utils.NotificationTestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = NotificationStatusController.class)
class NotificationStatusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificationStatusService notificationStatusServiceMock;


    @Test
    void setNotificationAsRead() throws Exception {
        NotificationResponseDTO notificationResponseDTO = NotificationTestUtil.getNotificationResponseDTO1();
        notificationResponseDTO.setStatus(NotificationStatusEnum.READ);
        when(notificationStatusServiceMock.setNotificationAsRead(1L)).thenReturn(notificationResponseDTO);
        when(notificationStatusServiceMock.setNotificationAsRead(2L)).thenThrow(new NotificationServiceException());
        when(notificationStatusServiceMock.setNotificationAsRead(111L)).thenThrow(new EntityNotFoundException());

        mockMvc.perform(put("/notifications-status/read/{notificationId}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.type").value("INFO"))
                .andExpect(jsonPath("$.status").value("READ"))
                .andExpect(jsonPath("$.content").value("Driver Bruce Wayne assigned to your booking"))
                .andExpect(jsonPath("$.sender").value("booking-service"))
                .andExpect(jsonPath("$.receiverUsername").value("adam123"))
                .andExpect(jsonPath("$.creationDate").value("2020-09-14 09:01:44"));

        mockMvc.perform(put("/notifications-status/read/{notificationId}", 2L))
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(put("/notifications-status/read/{notificationId}", 111L))
                .andExpect(status().isNotFound());
    }

    @Test
    void setNotificationAsDeleted() throws Exception {
        NotificationResponseDTO notificationResponseDTO = NotificationTestUtil.getNotificationResponseDTO1();
        notificationResponseDTO.setStatus(NotificationStatusEnum.DELETED);
        when(notificationStatusServiceMock.setNotificationAsDeleted(1L)).thenReturn(notificationResponseDTO);
        when(notificationStatusServiceMock.setNotificationAsDeleted(2L)).thenThrow(new NotificationServiceException());
        when(notificationStatusServiceMock.setNotificationAsDeleted(111L)).thenThrow(new EntityNotFoundException());

        mockMvc.perform(put("/notifications-status/delete/{notificationId}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.type").value("INFO"))
                .andExpect(jsonPath("$.status").value("DELETED"))
                .andExpect(jsonPath("$.content").value("Driver Bruce Wayne assigned to your booking"))
                .andExpect(jsonPath("$.sender").value("booking-service"))
                .andExpect(jsonPath("$.receiverUsername").value("adam123"))
                .andExpect(jsonPath("$.creationDate").value("2020-09-14 09:01:44"));

        mockMvc.perform(put("/notifications-status/delete/{notificationId}", 2L))
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(put("/notifications-status/delete/{notificationId}", 111L))
                .andExpect(status().isNotFound());
    }

}