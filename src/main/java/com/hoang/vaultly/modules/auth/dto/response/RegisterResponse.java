package com.hoang.vaultly.modules.auth.dto.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record RegisterResponse(
        UUID userId,
        String username,
        String displayName,
        String message) {
}
