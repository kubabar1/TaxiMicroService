package com.taximicroservice.driverservice.model;

import com.taximicroservice.driverservice.model.utils.RestPageImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverResponseDTOPage implements Serializable {

    RestPageImpl<DriverResponseDTO> userResponseDTORestPage;

}
