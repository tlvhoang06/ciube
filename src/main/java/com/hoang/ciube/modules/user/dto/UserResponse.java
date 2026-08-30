package com.hoang.ciube.modules.user.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserResponse(
        UUID userId,
        String phoneNumber,
        String displayName
) {
}
