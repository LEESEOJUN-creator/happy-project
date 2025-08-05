package com.example.happy.service;

import com.example.happy.domain.User;
import com.example.happy.domain.UserScore;
import com.example.happy.dto.UserScoreResponseDto;
import com.example.happy.repository.UserRepository;
import com.example.happy.repository.UserScoreRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserScoreService {

    private final UserScoreRepository userScoreRepository;
    private final UserRepository userRepository;

    @Transactional
    public void updateGameScore(Long userId, int game1, int game2) {
        User user = getUser(userId);

        UserScore userScore = userScoreRepository.findByUser(user)
                .orElseGet(() -> new UserScore(user, 0, 0, 0)); // 기본 생성자 대신 명시적 생성

        userScore.setGame1Score(game1);
        userScore.setGame2Score(game2);
        userScore.calculateTotal();

        userScoreRepository.save(userScore);
    }

    @Transactional
    public void addPostScore(Long userId) {
        User user = getUser(userId);

        UserScore userScore = userScoreRepository.findByUser(user)
                .orElseGet(() -> new UserScore(user, 0, 0, 0));

        userScore.setPostScore(userScore.getPostScore() + 10); // 글 1개당 10점
        userScore.calculateTotal();

        userScoreRepository.save(userScore);
    }

    @Transactional(readOnly = true)
    public List<UserScoreResponseDto> getRankings() {
        return userScoreRepository.findAllByOrderByTotalScoreDesc()
                .stream()
                .map(UserScoreResponseDto::from)
                .toList();
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("유저 없음"));
    }
}
