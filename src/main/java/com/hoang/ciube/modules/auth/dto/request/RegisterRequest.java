package com.hoang.ciube.modules.auth.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
        @NotBlank
        String displayName,

        @NotBlank
        String phoneNumber,

        @NotBlank
        String password
) {
}
