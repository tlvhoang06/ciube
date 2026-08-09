package com.hoang.ciube.modules.auth.service;

import com.hoang.ciube.common.exception.AppException;
import com.hoang.ciube.common.exception.ErrorCode;
import com.hoang.ciube.modules.auth.dto.request.AuthRequest;
import com.hoang.ciube.modules.auth.dto.response.AuthResponse;
import com.hoang.ciube.modules.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    public AuthResponse authenticate(AuthRequest request){
        var user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

    }

}
