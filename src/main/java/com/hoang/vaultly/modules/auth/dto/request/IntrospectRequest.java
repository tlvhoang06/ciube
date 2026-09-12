package com.hoang.vaultly.modules.auth.dto.request;

import jakarta.validation.constraints.NotBlank;

public record IntrospectRequest(
        @NotBlank
        String token
) {
}
