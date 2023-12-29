package com.myprecious.moneyglove.domain.user;

import com.myprecious.moneyglove.domain.user.UserResponse;
import com.myprecious.moneyglove.dto.ResponseDto;
import com.myprecious.moneyglove.domain.user.UserRequest;
import com.myprecious.moneyglove.domain.user.UserEntity;
import com.myprecious.moneyglove.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users") // 공통되는 URL 묶기.
public class UserController {
    private final UserService userService;

    @PostMapping("")
    public ResponseDto<UserEntity> createUser(@RequestBody UserRequest userRequest){
        ResponseDto<UserEntity> result = userService.createUser(userRequest);
        return result;
    }

    @GetMapping("/findAll")
    public ResponseDto<List<UserResponse>> findAll() {
        ResponseDto<List<UserResponse>> result = userService.findAll();
        return result;
    }

    @PatchMapping("/update/{userId}")
    public ResponseDto<UserEntity> updateClub(@PathVariable Long userId, @RequestBody UserUpdateRequest request) {
        ResponseDto<UserEntity> result = userService.updateUser(userId, request);
        return result;
    }
}

