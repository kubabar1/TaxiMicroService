package com.taximicroservice.passengerservice.controller;

import com.taximicroservice.passengerservice.service.PassengerService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

class PassengerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PassengerService passengerService;

    @BeforeAll
    static void initBeforeAll() {
    }

    @Test
    void getPassengersPage() {
    }

    @Test
    void addPassenger() {
    }

}