package com.taximicroservice.userservice.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoleDTO implements Serializable {

    private Long id;

    private String name;

}
