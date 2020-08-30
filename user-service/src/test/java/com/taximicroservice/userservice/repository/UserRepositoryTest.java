package com.taximicroservice.userservice.repository;

import com.taximicroservice.userservice.model.entity.UserEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static com.taximicroservice.passengerservice.model.utils.UserUtils.generateUserEntity;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;


    private static UserEntity user1;

    @BeforeAll
    static void initBeforeAll() {
        user1 = generateUserEntity();
    }

    @Test
    void findByRoleEntitySet_id() {
        Page<UserEntity> userEntityPage = userRepository.findByRoleEntitySet_id(1L, PageRequest.of(0, 5));

        assertEquals(2, userEntityPage.getTotalPages());
        assertEquals(9, userEntityPage.getTotalElements());
        assertEquals(0, userEntityPage.getNumber());
        assertEquals(5, userEntityPage.getNumberOfElements());
        assertEquals(5, userEntityPage.getSize());

        assertEquals(user1.getId(), userEntityPage.getContent().get(0).getId());
        assertEquals(user1.getUserName(), userEntityPage.getContent().get(0).getUserName());
        assertEquals(user1.getName(), userEntityPage.getContent().get(0).getName());
        assertEquals(user1.getSurname(), userEntityPage.getContent().get(0).getSurname());
        assertEquals(user1.getPesel(), userEntityPage.getContent().get(0).getPesel());
        assertEquals(user1.getPassword(), userEntityPage.getContent().get(0).getPassword());
        assertEquals(user1.getPasswordSalt(), userEntityPage.getContent().get(0).getPasswordSalt());
        assertEquals(user1.getBirthDate(), userEntityPage.getContent().get(0).getBirthDate());
        assertEquals(user1.getCreationDate(), userEntityPage.getContent().get(0).getCreationDate());
        assertEquals(user1.getEmail(), userEntityPage.getContent().get(0).getEmail());
        assertEquals(user1.getRoleEntitySet(), userEntityPage.getContent().get(0).getRoleEntitySet());
        assertEquals(user1.getUserSettings().getAppearance(), userEntityPage.getContent().get(0).getUserSettings().getAppearance());
        assertEquals(user1.getUserSettings().getLanguage(), userEntityPage.getContent().get(0).getUserSettings().getLanguage());
    }

}