package com.example.happy.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MypageResponseDto {
    private String username;
    private String email;
    private boolean verified;
}
