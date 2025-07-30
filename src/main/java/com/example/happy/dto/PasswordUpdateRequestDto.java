package com.example.happy.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PasswordUpdateRequestDto {
    private String currentPassword;
    private String newPassword;
}
