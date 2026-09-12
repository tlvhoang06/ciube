package com.hoang.vaultly.modules.user.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateProfileRequest(
        @NotBlank(message = "Display name cannot be blank")
        String displayName
) {
}
