package com.hoang.ciube.modules.auth.dto.response;

import lombok.Builder;

@Builder
public record IntrospectResponse(
        boolean isValid
) {
}
