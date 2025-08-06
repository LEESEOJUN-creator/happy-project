package com.example.happy.dto.response;

import com.example.happy.domain.UserScore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRankingResponseDto {

    private Long userId;
    private String username;

    private int game1Score;
    private int game2Score;
    private int postScore;
    private int totalScore;

    private int rank; // 1위부터 차례대로 매긴 순위

    public static UserRankingResponseDto of(UserScore us, int rank) {
        return UserRankingResponseDto.builder()
                .userId(us.getUser().getId())
                .username(us.getUser().getUsername())
                .game1Score(us.getGame1Score())
                .game2Score(us.getGame2Score())
                .postScore(us.getPostScore())
                .totalScore(us.getTotalScore())
                .rank(rank)
                .build();
    }
}
