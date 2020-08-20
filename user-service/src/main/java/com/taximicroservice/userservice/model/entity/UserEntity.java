package com.taximicroservice.userservice.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "pesel")
    private String pesel;

    @Column(name = "birth_date", columnDefinition = "DATE")
    private LocalDate birthDate;

    @Column(name = "password")
    private String password;

    @Column(name = "password_salt")
    private String passwordSalt;

    @Column(name = "creation_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime creationDate;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserSettingsEntity userSettings;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<RoleEntity> roleEntitySet = new HashSet<>();

    public void addRole(RoleEntity roleEntity) {
        roleEntitySet.add(roleEntity);
    }

    public void removeRole(RoleEntity roleEntity) {
        roleEntitySet.remove(roleEntity);
    }

}
