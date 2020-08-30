package com.taximicroservice.passengerservice.model;

import com.taximicroservice.passengerservice.model.utils.RestPageImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerResponseDTOPage implements Serializable {

    RestPageImpl<PassengerResponseDTO> userResponseDTORestPage;

}
