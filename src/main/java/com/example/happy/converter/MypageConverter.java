package com.example.happy.converter;

import com.example.happy.domain.User;
import com.example.happy.dto.response.MypageResponseDto;
import org.springframework.stereotype.Component;

@Component
public class MypageConverter {
    public MypageResponseDto tomypageResponse(User user) {
        return new MypageResponseDto(
                user.getUsername(),
                user.getEmail(),
                user.isVerified()
        );
    }

}
