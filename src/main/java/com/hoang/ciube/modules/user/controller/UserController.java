package com.hoang.ciube.modules.user.controller;

import com.hoang.ciube.modules.user.dto.request.UpdateProfileRequest;
import com.hoang.ciube.modules.user.dto.response.UserResponse;
import com.hoang.ciube.modules.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public UserResponse getMyProfile(){
        return userService.getMyProfile();
    }

    @PostMapping("/me")
    public UserResponse updateProfile(@Valid @RequestBody UpdateProfileRequest request){
        return userService.updateProfile(request);
    }
}
