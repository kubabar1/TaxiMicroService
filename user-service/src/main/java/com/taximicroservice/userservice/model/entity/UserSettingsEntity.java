package com.taximicroservice.userservice.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import javax.persistence.MapsId;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.io.Serializable;

@Data
@Entity
@Table(name = "user_settings")
public class UserSettingsEntity implements Serializable {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "language_code", nullable=false)
    private LanguageEntity language;

    @ManyToOne
    @JoinColumn(name = "appearance_code", nullable=false)
    private AppearanceEntity appearance;

    @OneToOne
    @MapsId
    private UserEntity user;

    @Override
    public String toString() {
        return "UserSettingsEntity{" +
                "userId=" + userId +
                ", language=" + language +
                ", appearance=" + appearance +
                '}';
    }

}
