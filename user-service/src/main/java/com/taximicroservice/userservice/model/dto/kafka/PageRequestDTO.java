package com.taximicroservice.userservice.model.dto.kafka;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageRequestDTO implements Serializable {

    int page;

    int count;

}
