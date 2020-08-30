package com.taximicroservice.passengerservice.controller;

import com.taximicroservice.passengerservice.exception.PassengerServiceException;
import com.taximicroservice.passengerservice.model.PassengerAddDTO;
import com.taximicroservice.passengerservice.model.PassengerResponseDTO;
import com.taximicroservice.passengerservice.model.utils.RestPageImpl;
import com.taximicroservice.passengerservice.service.PassengerService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.taximicroservice.passengerservice.utils.PassengerUtils.generatePassengerResponseDTO;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PassengerController.class)
class PassengerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PassengerService passengerServiceMock;

    static PassengerResponseDTO passengerResponseDTO;

    @BeforeAll
    static void initBeforeAll() {
        passengerResponseDTO = generatePassengerResponseDTO();
    }

    @Test
    void getPassengersPage() throws Exception {
        List<PassengerResponseDTO> testPassengersList = new ArrayList<>();
        testPassengersList.add(passengerResponseDTO);
        testPassengersList.add(passengerResponseDTO);

        when(passengerServiceMock.getPassengersPage(0, 2)).thenReturn(new RestPageImpl<>(testPassengersList));
        when(passengerServiceMock.getPassengersPage(0, 0)).thenThrow(PassengerServiceException.class);

        mockMvc.perform(get("/passengers").param("page", "0").param("count", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size").value(2))
                .andExpect(jsonPath("$.number").value(0))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.totalElements").value(2))
                .andExpect(jsonPath("$.empty").value(false))
                .andExpect(jsonPath("$.first").value(true))
                .andExpect(jsonPath("$.content[0].id").value(1L))
                .andExpect(jsonPath("$.content[0].userName").value("adam123"))
                .andExpect(jsonPath("$.content[0].email").value("adam@qwerty.com"))
                .andExpect(jsonPath("$.content[0].name").value("Adam"))
                .andExpect(jsonPath("$.content[0].surname").value("Kowalski"))
                .andExpect(jsonPath("$.content[0].pesel").value("92111000000"))
                .andExpect(jsonPath("$.content[0].birthDate").value("1992-11-10"))
                .andExpect(jsonPath("$.content[0].creationDate").value("2013-02-14T06:01:17"))
                .andExpect(jsonPath("$.content[0].userSettings.appearance.appearanceCode").value("lt"))
                .andExpect(jsonPath("$.content[0].userSettings.appearance.name").value("light"))
                .andExpect(jsonPath("$.content[0].userSettings.language.languageCode").value("en"))
                .andExpect(jsonPath("$.content[0].userSettings.language.name").value("English"))
                .andExpect(jsonPath("$.content[0].userRole[0].id").value(1))
                .andExpect(jsonPath("$.content[0].userRole[0].name").value("passenger"));

        mockMvc.perform(get("/passengers").param("page", "0").param("count", "0"))
                .andExpect(status().isUnprocessableEntity());

        when(passengerServiceMock.getPassengersPage(0, 2)).thenReturn(new RestPageImpl<>(new ArrayList<>()));
        mockMvc.perform(get("/passengers").param("page", "0").param("count", "2"))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.size").value(0))
                .andExpect(jsonPath("$.number").value(0))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.totalElements").value(0))
                .andExpect(jsonPath("$.empty").value(true))
                .andExpect(jsonPath("$.first").value(true));
    }

    @Test
    void addPassenger() throws Exception {
        PassengerAddDTO passengerAddDTO = new PassengerAddDTO();
        passengerAddDTO.setUserName("adam123");
        passengerAddDTO.setName("Adam");
        passengerAddDTO.setSurname("Kowalski");
        passengerAddDTO.setPesel("92111000000");
        passengerAddDTO.setEmail("adam@qwerty.com");
        passengerAddDTO.setPassword("qwerty");
        passengerAddDTO.setBirthDate(LocalDate.parse("1992-11-10"));

        when(passengerServiceMock.addPassenger(passengerAddDTO)).thenReturn(passengerResponseDTO);

        String passengerAddString = "{\n" +
                "    \"userName\":\"adam123\",\n" +
                "    \"email\":\"adam@qwerty.com\",\n" +
                "    \"name\":\"Adam\",\n" +
                "    \"surname\":\"Kowalski\",\n" +
                "    \"pesel\":\"92111000000\",\n" +
                "    \"password\":\"qwerty\",\n" +
                "    \"birthDate\":\"10-11-1992\"\n" +
                "}";

        mockMvc.perform(post("/passengers")
                .content(passengerAddString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.userName").value("adam123"))
                .andExpect(jsonPath("$.email").value("adam@qwerty.com"))
                .andExpect(jsonPath("$.name").value("Adam"))
                .andExpect(jsonPath("$.surname").value("Kowalski"))
                .andExpect(jsonPath("$.pesel").value("92111000000"))
                .andExpect(jsonPath("$.birthDate").value("1992-11-10"))
                .andExpect(jsonPath("$.creationDate").value("2013-02-14T06:01:17"))
                .andExpect(jsonPath("$.userSettings.appearance.appearanceCode").value("lt"))
                .andExpect(jsonPath("$.userSettings.appearance.name").value("light"))
                .andExpect(jsonPath("$.userSettings.language.languageCode").value("en"))
                .andExpect(jsonPath("$.userSettings.language.name").value("English"))
                .andExpect(jsonPath("$.userRole[0].id").value(1))
                .andExpect(jsonPath("$.userRole[0].name").value("passenger"));
    }

}