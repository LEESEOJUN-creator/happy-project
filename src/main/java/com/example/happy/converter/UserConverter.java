package com.example.happy.converter;

import com.example.happy.domain.User;
import com.example.happy.dto.request.SignupRequestDto;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public User toUser(SignupRequestDto dto, String encodedPassword) {
        return User.builder()
                .email(dto.getEmail())
                .username(dto.getUsername())
                .password(encodedPassword)
                .profileUrl(dto.getProfileUrl())
                .verified(false)
                .build();

    }
}
