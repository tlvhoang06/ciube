package com.hoang.ciube.modules.auth.service;

import com.hoang.ciube.common.exception.AppException;
import com.hoang.ciube.common.exception.ErrorCode;
import com.hoang.ciube.modules.auth.dto.request.AuthRequest;
import com.hoang.ciube.modules.auth.dto.response.AuthResponse;
import com.hoang.ciube.modules.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
                .tokenType(JwtService.TOKEN_TYPE)
                .expiresIn(jwtService.getAccessTokenDuration())
                .build();
    }

}
