package com.hoang.vaultly.modules.auth.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AuthRequest(
    @NotBlank
    String username,
    @NotBlank
    String password
) {}
