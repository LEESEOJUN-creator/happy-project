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
    private final UserRepository     userRepository;

    /* ───────────── 1) 게임 점수 반영 ───────────── */
    @Transactional
    public void updateGameScore(Long userId, int game1, int game2) {

        UserScore score = findOrCreate(userId);

        score.setGame1Score(game1);
        score.setGame2Score(game2);
        score.calculateTotal();        // 총점 재계산
        /* save() 생략
           → 영속 상태 엔티티이므로 dirty checking 으로 자동 flush */
    }

    /* ───────────── 2) 게시글 작성 점수 반영 ───────────── */
    @Transactional
    public void addPostScore(Long userId) {

        UserScore score = findOrCreate(userId);

        score.setPostScore(score.getPostScore() + 10); // 게시글 1개 = 10점
        score.calculateTotal();
    }

    /* ───────────── 3) 랭킹 조회 ───────────── */
    @Transactional(readOnly = true)
    public List<UserScoreResponseDto> getRankings() {
        // totalScore DESC 정렬
        return userScoreRepository.findAllByOrderByTotalScoreDesc()
                .stream()
                .map(UserScoreResponseDto::from)
                .toList();
    }

    /* ─────────────────────────────────────────── */
    /** 기존 점수가 없으면 새로 만들어 반환 */
    private UserScore findOrCreate(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("유저 없음"));

        return userScoreRepository.findByUser(user)
                .orElseGet(() -> userScoreRepository.save(
                        new UserScore(user, 0, 0, 0)
                ));
    }
}
