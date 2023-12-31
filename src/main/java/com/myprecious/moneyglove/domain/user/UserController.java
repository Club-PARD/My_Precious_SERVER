package com.myprecious.moneyglove.domain.user;

import com.myprecious.moneyglove.common.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v9/users") // 공통되는 URL 묶기.
public class UserController {
    private final UserService userService;

    @PostMapping("")
    public ResponseDto<UserEntity> createUser(@RequestBody UserRequest userRequest) {
        ResponseDto<UserEntity> result = userService.createUser(userRequest);
        return result;
    }

    @GetMapping("")
    public ResponseDto<List<UserResponse>> findAll() {
        ResponseDto<List<UserResponse>> result = userService.findAll();
        return result;
    }

    @PatchMapping("/{uId}")
    public ResponseDto<UserEntity> updateClub(@PathVariable String uId, @RequestBody UserUpdateRequest request) {
        ResponseDto<UserEntity> result = userService.updateUser(uId, request);
        return result;
    }
}

