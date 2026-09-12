package com.hoang.vaultly.modules.auth.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
        @NotBlank
        String displayName,

        @NotBlank
        String username,

        @NotBlank
        String password
) {
}
