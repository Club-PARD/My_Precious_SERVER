package com.myprecious.moneyglove.domain.user;

import com.myprecious.moneyglove.domain.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByUid(String uid);
    UserEntity findByUid(String uid);
}
