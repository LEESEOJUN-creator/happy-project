package com.example.happy.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequestDto {

    private String email;    // 로그인 이메일
    private String password; // 비밀번호 (암호화 전)
}

