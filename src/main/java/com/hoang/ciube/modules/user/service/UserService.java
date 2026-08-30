package com.hoang.ciube.modules.user.service;

import com.hoang.ciube.common.exception.AppException;
import com.hoang.ciube.common.exception.ErrorCode;
import com.hoang.ciube.modules.user.dto.UserResponse;
import com.hoang.ciube.modules.user.entity.User;
import com.hoang.ciube.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private User getCurrentUser() {
        String phoneNumber = Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getName();
        return userRepository
                .findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    }

    public UserResponse getMyProfile() {
        User user = getCurrentUser();
        return UserResponse
                .builder()
                .userId(user.getUserId())
                .phoneNumber(user.getPhoneNumber())
                .displayName(user.getDisplayName())
                .build();
    }
}
