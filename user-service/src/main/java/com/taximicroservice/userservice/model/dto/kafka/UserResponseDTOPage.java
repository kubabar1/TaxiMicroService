package com.taximicroservice.userservice.model.dto.kafka;

import com.taximicroservice.userservice.model.dto.UserResponseDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTOPage implements Serializable {

    Page<UserResponseDTO> userResponseDTORestPage;

}
