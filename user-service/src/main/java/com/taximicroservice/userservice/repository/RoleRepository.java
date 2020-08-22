package com.taximicroservice.userservice.repository;

import com.taximicroservice.userservice.model.entity.RoleEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleRepository extends PagingAndSortingRepository<RoleEntity, Long> {
}
