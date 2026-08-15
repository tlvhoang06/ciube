package com.hoang.ciube.modules.auth.service;

import com.hoang.ciube.common.exception.AppException;
import com.hoang.ciube.common.exception.ErrorCode;
import com.hoang.ciube.modules.auth.dto.request.AuthRequest;
import com.hoang.ciube.modules.auth.dto.request.IntrospectRequest;
import com.hoang.ciube.modules.auth.dto.request.RefreshRequest;
import com.hoang.ciube.modules.auth.dto.request.RegisterRequest;
import com.hoang.ciube.modules.auth.dto.response.AuthResponse;
import com.hoang.ciube.modules.auth.dto.response.IntrospectResponse;
import com.hoang.ciube.modules.auth.dto.response.RegisterResponse;
import com.hoang.ciube.modules.auth.entity.User;
import com.hoang.ciube.modules.auth.repository.UserRepository;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse authenticate(AuthRequest request) {
        var user = userRepository.findByPhoneNumber(request.phoneNumber())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new AppException(ErrorCode.INVALID_CREDENTIALS);
        }

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return AuthResponse
                .builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType("Bearer")
                .expiresIn(jwtService.getAccessTokenDuration())
                .build();
    }

    @Transactional
    public RegisterResponse register(RegisterRequest request) {
        if (userRepository.existsByPhoneNumber(request.phoneNumber()))
            throw new AppException(ErrorCode.PHONE_NUMBER_EXISTED);
        User user = new User();
        user.setPhoneNumber(request.phoneNumber());
        user.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);
        return RegisterResponse
                .builder()
                .userId(user.getUserId())
                .phoneNumber(user.getPhoneNumber())
                .message("Register success")
                .build();
    }

    public IntrospectResponse introspect(IntrospectRequest request) {
        try {
            jwtService.validateToken(request.token(), JwtService.ACCESS_TYPE);
            return IntrospectResponse.builder().isValid(true).build();
        } catch (AppException e) {
            return IntrospectResponse.builder().isValid(false).build();
        }
    }

    public AuthResponse refreshToken(RefreshRequest request) {
        // throw AppException if refresh token is invalid/expired
        JWTClaimsSet claimsSet = jwtService.validateToken(request.refreshToken(), JwtService.REFRESH_TYPE);

        String phoneNumber = claimsSet.getSubject(); // phone number
        User user = userRepository
                .findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        // generate new tokens
        String newRefreshToken = jwtService.generateRefreshToken(user);
        String newAccessToken = jwtService.generateAccessToken(user);

        return AuthResponse
                .builder()
                .refreshToken(newRefreshToken)
                .accessToken(newAccessToken)
                .expiresIn(jwtService.getAccessTokenDuration())
                .tokenType("Bearer")
                .build();
    }

}
