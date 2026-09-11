package com.hoang.ciube.modules.auth.dto.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record RegisterResponse(
        UUID userId,
        String username,
        String displayName,
        String message) {
}
