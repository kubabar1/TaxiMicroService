package com.taximicroservice.userservice.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import java.io.Serializable;

@Data
@Entity
@Table(name = "languages")
public class LanguageEntity implements Serializable {

    @Id
    @Column(name = "language_code")
    private String languageCode;

    @Column(name = "name")
    private String name;

}
