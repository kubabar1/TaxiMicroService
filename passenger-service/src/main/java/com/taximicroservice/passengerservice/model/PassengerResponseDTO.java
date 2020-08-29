package com.taximicroservice.passengerservice.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class PassengerResponseDTO implements Serializable {

    private Long id;

    private String userName;

    private String email;

    private String name;

    private String surname;

    private String pesel;

    private LocalDate birthDate;

}
