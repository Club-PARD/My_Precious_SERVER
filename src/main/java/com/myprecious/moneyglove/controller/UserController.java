package com.myprecious.moneyglove.controller;

import com.myprecious.moneyglove.dto.ResponseDto;
import com.myprecious.moneyglove.dto.User.UserCreateRequestDto;
import com.myprecious.moneyglove.entity.UserEntity;
import com.myprecious.moneyglove.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/moneyglove/user") // 공통되는 URL 묶기.
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public ResponseDto<UserEntity> createUser(@RequestBody UserCreateRequestDto userRequest){
        ResponseDto<UserEntity> result = userService.createUser(userRequest);
        return result;
    }

    @GetMapping("/findAll")
    public ResponseDto<List<UserEntity>> finAll(){
        ResponseDto<List<UserEntity>> result = userService.findAll();
        return result;
    }
}

