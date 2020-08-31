package com.taximicroservice.driverservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestDTO implements Serializable {

    int page;

    int count;

}
