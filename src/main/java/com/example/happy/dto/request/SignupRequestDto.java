package com.example.happy.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignupRequestDto {
    private String email;
    private String password;
    private String username;
    private String profileUrl;
}
