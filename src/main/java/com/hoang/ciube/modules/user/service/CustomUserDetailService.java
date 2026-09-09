package com.hoang.ciube.modules.user.service;

import com.hoang.ciube.common.exception.AppException;
import com.hoang.ciube.common.exception.ErrorCode;
import com.hoang.ciube.modules.user.entity.User;
import com.hoang.ciube.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) {
        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities("...")
                .build();
    }

}
