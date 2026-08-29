package com.hoang.ciube.modules.user.controller;

import com.hoang.ciube.modules.user.dto.UserResponse;
import com.hoang.ciube.modules.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private UserService userService;

    @GetMapping("/me")
    public UserResponse getMyProfile(){
        return userService.getMyProfile();
    }
}
