package com.hoang.ciube.modules.auth.dto.request;

public record RegisterRequest(
        String displayName,
        String phoneNumber,
        String password
) {
}
