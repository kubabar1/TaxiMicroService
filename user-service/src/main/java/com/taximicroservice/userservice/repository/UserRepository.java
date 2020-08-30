package com.taximicroservice.userservice.repository;

import com.taximicroservice.userservice.model.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {

    Page<UserEntity> findByRoleEntitySet_id(Long roleId, Pageable pageable);

}
