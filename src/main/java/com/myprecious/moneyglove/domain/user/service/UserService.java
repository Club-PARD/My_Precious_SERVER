package com.myprecious.moneyglove.domain.user.service;

import com.myprecious.moneyglove.common.dto.ResponseDto;
import com.myprecious.moneyglove.domain.user.repository.UserRepository;
import com.myprecious.moneyglove.domain.user.dto.request.UserRequest;
import com.myprecious.moneyglove.domain.user.dto.request.UserUpdateRequest;
import com.myprecious.moneyglove.domain.user.dto.response.UserResponse;
import com.myprecious.moneyglove.domain.user.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseDto<UserEntity> createUser(UserRequest userRequest) {
        String uid = userRequest.getUid();

        try {
            if (userRepository.existsByUid(uid)) {
                UserEntity existedUser = userRepository.findByUid(uid);
                return ResponseDto.setFailed("같은 아이디의 유저 존재함. 이미 가입된 유저", existedUser);
            }
            UserEntity userEntity = userRequest.toEntity();
            userRepository.save(userEntity);
            return ResponseDto.setSuccess("유저 등록 완료", userEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("Database error");
        }
    }

    public ResponseDto<List<UserResponse>> findAll() {
        List<UserResponse> userResponses;
        try {
            userResponses = userRepository.findAll().stream()
                    .map(UserResponse::new)
                    .collect(Collectors.toList());
            if (userResponses.isEmpty())
                return ResponseDto.setFailed("리스트가 비어있습니다."); //아무것도 없을 때
            int total = userResponses.size();
            return ResponseDto.setSuccess("유저 리스트 입니다", userResponses, total);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("데이터 베이스 오류");
        }
    }

    @Transactional
    public ResponseDto<UserEntity> updateUser(String uId, UserUpdateRequest request) {
        try {
            UserEntity user = userRepository.findByUid(uId);
            if (user == null) {
                return ResponseDto.setFailed("해당 이름의 유저가 없습니다.");
            }

            // 이름, 생일, 전화번호 중 하나라도 값이 없으면 업데이트를 허용하지 않음
            if (!StringUtils.hasText(request.getName()) || !isBirth(request) || !StringUtils.hasText(request.getPhoneNum())) {
                return ResponseDto.setFailed("이름, 생일, 전화번호는 필수 값입니다.");
            }

            if (StringUtils.hasText(request.getName())) {
                user.setName(request.getName()); // 이름 수정
            }
            if (isBirth(request)) {
                user.setBirth(request.getBirth()); // 생일 수정
            }
            if (StringUtils.hasText(request.getPhoneNum())) {
                user.setPhoneNum(request.getPhoneNum()); // 전화번호 수정
            }
            user.update(request);

            return ResponseDto.setSuccess("성공적으로 업데이트 되었습니다", user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("데이터 베이스 오류");
        }
    }

    private static boolean isBirth(UserUpdateRequest request) {
        return request.getBirth() != null;
    }
}
