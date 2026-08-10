package com.hoang.ciube.modules.auth.dto.response;

import lombok.Builder;

@Builder
public record AuthResponse(
        String accessToken, // for jwt
        String refreshToken,
        String tokenType,
        long expiresIn
) {
}
