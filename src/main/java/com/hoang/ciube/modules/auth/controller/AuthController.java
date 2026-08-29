package com.hoang.ciube.modules.auth.controller;


import com.hoang.ciube.modules.auth.dto.request.AuthRequest;
import com.hoang.ciube.modules.auth.dto.request.IntrospectRequest;
import com.hoang.ciube.modules.auth.dto.request.RefreshRequest;
import com.hoang.ciube.modules.auth.dto.request.RegisterRequest;
import com.hoang.ciube.modules.auth.dto.response.AuthResponse;
import com.hoang.ciube.modules.auth.dto.response.IntrospectResponse;
import com.hoang.ciube.modules.auth.dto.response.RegisterResponse;
import com.hoang.ciube.modules.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody AuthRequest request){
        return authService.authenticate(request);
    }

    @PostMapping("/register")
    public RegisterResponse register(@Valid @RequestBody RegisterRequest request){
        return authService.register(request);
    }

    @PostMapping("/introspect")
    public IntrospectResponse introspect(@Valid @RequestBody IntrospectRequest request){
        return authService.introspect(request);
    }

    @PostMapping("/refresh")
    public AuthResponse refresh(@Valid @RequestBody RefreshRequest request){
        return authService.refreshToken(request);
    }



}
