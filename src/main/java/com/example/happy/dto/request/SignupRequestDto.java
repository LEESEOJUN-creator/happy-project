// src/main/java/com/example/happy/dto/request/SignupRequestDto.java
package com.example.happy.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SignupRequestDto {

    @Email @NotBlank
    private String email;

    @NotBlank
    @Size(min = 8, max = 64)
    private String password;

    @NotBlank                       // ✅ username 반드시 받기
    private String username;
}
