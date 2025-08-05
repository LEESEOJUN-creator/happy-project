package com.example.happy.domain;

import com.example.happy.domain.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class UserScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    private int game1Score;
    private int game2Score;
    private int postScore;
    private int totalScore;

    public void calculateTotal() {
        this.totalScore = game1Score + game2Score + postScore;
    }

    public UserScore(User user, int game1Score, int game2Score, int postScore) {
        this.user = user;
        this.game1Score = game1Score;
        this.game2Score = game2Score;
        this.postScore = postScore;
        calculateTotal();
    }
}
