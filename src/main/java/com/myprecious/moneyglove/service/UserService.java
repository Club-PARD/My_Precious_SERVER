package com.myprecious.moneyglove.service;

import com.myprecious.moneyglove.dto.ResponseDto;
import com.myprecious.moneyglove.dto.User.UserCreateRequestDto;
import com.myprecious.moneyglove.entity.UserEntity;
import com.myprecious.moneyglove.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseDto<UserEntity> createUser(UserCreateRequestDto userCreateRequest) {
        String userId = userCreateRequest.getUserId();
        try {
            if (userRepository.existsByUserId(userId)) {
                return ResponseDto.setFailed("같은 아이디의 유저 존재함. 이미 가입된 유저");
            }

            UserEntity userEntity = userCreateRequest.toEntity();
            userRepository.save(userEntity);
            return ResponseDto.setSuccess("유저 등록 완료", userEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("Database error");
        }
    }
}
