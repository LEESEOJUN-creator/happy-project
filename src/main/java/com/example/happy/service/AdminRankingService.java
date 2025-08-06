package com.example.happy.service;

import com.example.happy.domain.UserScore;
import com.example.happy.repository.UserScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminRankingService {

    private final UserScoreRepository scoreRepo;

    public List<UserScore> getTop10Rankings() {
        return scoreRepo.findTop10ByOrderByTotalScoreDesc();
    }

    public void rewardUser(Long userId) {
        UserScore score = scoreRepo.findAll().stream()
                .filter(s -> s.getUser().getId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("점수 없음"));

        if (score.isRewarded()) {
            throw new IllegalStateException("이미 상품 지급됨");
        }

        score.setRewarded(true);
        scoreRepo.save(score);
    }
}