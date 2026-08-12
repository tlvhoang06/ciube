package com.hoang.ciube.modules.auth.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AuthRequest(
    @NotBlank
    String phoneNumber,
    @NotBlank
    String password
) {}
