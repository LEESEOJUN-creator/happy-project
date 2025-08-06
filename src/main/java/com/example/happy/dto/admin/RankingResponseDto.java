package com.example.happy.dto.admin;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RankingResponseDto {
    private Long userId;
    private String username;
    private int game1Score;
    private int game2Score;
    private int postScore;
    private int totalScore;
    private boolean rewarded;
}