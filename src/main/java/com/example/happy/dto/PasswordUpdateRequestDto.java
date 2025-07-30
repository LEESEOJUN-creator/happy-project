package com.example.happy.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PasswordUpdateRequestDto {

    @NotBlank(message = "현재 비밀번호를 입력해주세요.")     // 유지 권장
    private String currentPassword;

    @NotBlank(message = "새 비밀번호를 입력해주세요.")       // 필수
    @Size(min = 8, max = 64, message = "비밀번호는 8~64자여야 합니다.")
    private String newPassword;
}

