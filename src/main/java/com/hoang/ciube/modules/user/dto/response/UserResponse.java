package com.hoang.ciube.modules.user.dto.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserResponse(
        UUID userId,
        String phoneNumber,
        String displayName
) {
}
