package com.myprecious.moneyglove.service;

import com.myprecious.moneyglove.dto.ResponseDto;
import com.myprecious.moneyglove.dto.User.UserRequest;
import com.myprecious.moneyglove.entity.UserEntity;
import com.myprecious.moneyglove.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseDto<UserEntity> createUser(UserRequest userRequest) {
        String userId = userRequest.getUserId();
        try {
            if (userRepository.existsByUserId(userId)) {
                return ResponseDto.setFailed("같은 아이디의 유저 존재함. 이미 가입된 유저");
            }

            UserEntity userEntity = userRequest.toEntity();
            userRepository.save(userEntity);
            return ResponseDto.setSuccess("유저 등록 완료", userEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("Database error");
        }
    }

    // 전체 club list 출력
    public ResponseDto<List<UserEntity>> findAll() {
        List<UserEntity> users;
        try {
            users = userRepository.findAll();
            if(users.isEmpty())
                return ResponseDto.setFailed("리스트가 비어있습니다."); //아무것도 없을 때
            int total = users.size();
            return ResponseDto.setSuccess("유저 리스트 입니다",users, total);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseDto.setFailed("데이터 베이스 오류");
        }
    }
}
