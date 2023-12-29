package com.myprecious.moneyglove.controller;

import com.myprecious.moneyglove.user.UserResponse;
import com.myprecious.moneyglove.dto.ResponseDto;
import com.myprecious.moneyglove.user.UserRequest;
import com.myprecious.moneyglove.user.UserEntity;
import com.myprecious.moneyglove.user.UserService;
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
}

