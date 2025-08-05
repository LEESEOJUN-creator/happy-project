package com.example.happy.dto;

import com.example.happy.domain.UserScore;
import lombok.Getter;

@Getter
public class UserScoreResponseDto {

    private Long userId;
    private String username;
    private int game1Score;
    private int game2Score;
    private int postScore;
    private int totalScore;

    public static UserScoreResponseDto from(UserScore score) {
        UserScoreResponseDto dto = new UserScoreResponseDto();
        dto.userId = score.getUser().getId();
        dto.username = score.getUser().getUsername(); // User 클래스에 getUsername()이 있어야 함
        dto.game1Score = score.getGame1Score();
        dto.game2Score = score.getGame2Score();
        dto.postScore = score.getPostScore();
        dto.totalScore = score.getTotalScore();
        return dto;
    }
}
