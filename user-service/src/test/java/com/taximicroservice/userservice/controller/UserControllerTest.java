package com.taximicroservice.userservice.controller;

import com.taximicroservice.userservice.exception.UserServiceException;
import com.taximicroservice.userservice.model.dto.*;
import com.taximicroservice.userservice.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.taximicroservice.userservice.utils.UserUtils.generateUserResponseDTO;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userServiceMock;

    private static UserResponseDTO user1;


    @BeforeAll
    static void initBeforeAll() {
        user1 = generateUserResponseDTO();
    }

    @Test
    void getUsersPage() throws Exception {
        List<UserResponseDTO> testUsersList = new ArrayList<>();
        testUsersList.add(user1);
        testUsersList.add(user1);

        when(userServiceMock.getUsersPage(0, 2)).thenReturn(new PageImpl<>(testUsersList));
        when(userServiceMock.getUsersPage(0, 0)).thenThrow(UserServiceException.class);

        mockMvc.perform(get("/users").param("page", "0").param("count", "2"))
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
                .andExpect(jsonPath("$.content[0].userRole[0].name").value("passenger"))
                .andExpect(jsonPath("$.content[0].userRole[1].id").value(4))
                .andExpect(jsonPath("$.content[0].userRole[1].name").value("accountant"))
                .andExpect(jsonPath("$.content[0].userRole[2].id").value(2))
                .andExpect(jsonPath("$.content[0].userRole[2].name").value("driver"))
                .andExpect(jsonPath("$.content[0].userRole[3].id").value(3))
                .andExpect(jsonPath("$.content[0].userRole[3].name").value("admin"));

        mockMvc.perform(get("/users").param("page", "0").param("count", "0"))
                .andExpect(status().isUnprocessableEntity());


        when(userServiceMock.getUsersPage(0, 2)).thenReturn(new PageImpl<>(new ArrayList<>()));
        mockMvc.perform(get("/users").param("page", "0").param("count", "2"))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.size").value(0))
                .andExpect(jsonPath("$.number").value(0))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.totalElements").value(0))
                .andExpect(jsonPath("$.empty").value(true))
                .andExpect(jsonPath("$.first").value(true));
    }

    @Test
    void getUserById() throws Exception {
        when(userServiceMock.getUserWithId(1L)).thenReturn(user1);
        when(userServiceMock.getUserWithId(111L)).thenThrow(EntityNotFoundException.class);

        mockMvc.perform(get("/users/{userId}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
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
                .andExpect(jsonPath("$.userRole[0].name").value("passenger"))
                .andExpect(jsonPath("$.userRole[1].id").value(4))
                .andExpect(jsonPath("$.userRole[1].name").value("accountant"))
                .andExpect(jsonPath("$.userRole[2].id").value(2))
                .andExpect(jsonPath("$.userRole[2].name").value("driver"))
                .andExpect(jsonPath("$.userRole[3].id").value(3))
                .andExpect(jsonPath("$.userRole[3].name").value("admin"));

        mockMvc.perform(get("/users/{userId}", 111L))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteUserById() throws Exception {
        when(userServiceMock.deleteUserWithId(1L)).thenReturn(user1);
        when(userServiceMock.deleteUserWithId(111L)).thenThrow(EntityNotFoundException.class);

        mockMvc.perform(delete("/users/{userId}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
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
                .andExpect(jsonPath("$.userRole[0].name").value("passenger"))
                .andExpect(jsonPath("$.userRole[1].id").value(4))
                .andExpect(jsonPath("$.userRole[1].name").value("accountant"))
                .andExpect(jsonPath("$.userRole[2].id").value(2))
                .andExpect(jsonPath("$.userRole[2].name").value("driver"))
                .andExpect(jsonPath("$.userRole[3].id").value(3))
                .andExpect(jsonPath("$.userRole[3].name").value("admin"));

        mockMvc.perform(delete("/users/{userId}", 111L))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateUserWithId() throws Exception {
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        userUpdateDTO.setName("John");
        userUpdateDTO.setSurname("Wayne");
        userUpdateDTO.setPassword("qwertyuiop");
        userUpdateDTO.setPesel("00000000000");
        userUpdateDTO.setBirthDate(LocalDate.parse("2001-12-12"));

        when(userServiceMock.updateUserWithId(1L, userUpdateDTO)).thenReturn(userUpdateDTO);
        when(userServiceMock.updateUserWithId(111L, userUpdateDTO)).thenThrow(EntityNotFoundException.class);

        String updateJsonString = "{\"name\":\"John\",\"surname\":\"Wayne\",\"pesel\":\"00000000000\"," +
                "\"birthDate\":\"2001-12-12\",\"password\":\"qwertyuiop\"}";

        mockMvc.perform(put("/users/{userId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateJsonString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.surname").value("Wayne"))
                .andExpect(jsonPath("$.pesel").value("00000000000"))
                .andExpect(jsonPath("$.birthDate").value("2001-12-12"));


        mockMvc.perform(put("/users/{userId}", 111L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateJsonString))
                .andExpect(status().isNotFound());
    }

}