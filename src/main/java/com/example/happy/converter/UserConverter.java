package com.example.happy.converter;

import com.example.happy.domain.User;
import com.example.happy.dto.request.SignupRequestDto;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public static User toUser(SignupRequestDto dto, String encodedPassword, String studentCardUrl) {
        return User.builder()
                .email(dto.getEmail())
                .password(encodedPassword)
                .username(dto.getUsername())
                .studentCardUrl(studentCardUrl)
                .verified(false)
                .build();
    }

}
