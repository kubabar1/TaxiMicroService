package com.taximicroservice.userservice.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import java.io.Serializable;

@Data
@Entity
@Table(name = "appearances")
public class AppearanceEntity implements Serializable {

    @Id
    @Column(name = "appearance_code")
    private String appearanceCode;

    @Column(name = "name")
    private String name;

}
