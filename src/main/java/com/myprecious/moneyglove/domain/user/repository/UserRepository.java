package com.myprecious.moneyglove.domain.user.repository;

import com.myprecious.moneyglove.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    boolean existsByUid(String uid);

    UserEntity findByUid(String uid);
}
