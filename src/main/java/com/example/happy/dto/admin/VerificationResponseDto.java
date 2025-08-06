package com.example.happy.dto.admin;

import com.example.happy.enums.VerificationStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VerificationResponseDto {
    private Long id;
    private Long userId;
    private String imageUrl;
    private VerificationStatus status;
}