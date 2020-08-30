package com.taximicroservice.passengerservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestDTO implements Serializable {

    int page;

    int count;

}
