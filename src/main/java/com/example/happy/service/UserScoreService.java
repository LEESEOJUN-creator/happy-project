package com.example.happy.service;

import com.example.happy.domain.User;
import com.example.happy.domain.UserScore;
import com.example.happy.dto.response.UserRankingResponseDto;
import com.example.happy.dto.response.UserScoreResponseDto;
import com.example.happy.repository.UserRepository;
import com.example.happy.repository.UserScoreRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserScoreService {

    private final UserScoreRepository userScoreRepository;
    private final UserRepository userRepository;

    @Transactional
    public void updateGameScore(Long userId, int game1, int game2) {
        UserScore score = findOrCreate(userId);
        score.setGame1Score(game1);
        score.setGame2Score(game2);
        score.calculateTotal();
    }

    @Transactional
    public void addPostScore(Long userId) {
        UserScore score = findOrCreate(userId);
        score.setPostScore(score.getPostScore() + 10);
        score.calculateTotal();
    }

    @Transactional(readOnly = true)
    public List<UserScoreResponseDto> getRankings() {
        return userScoreRepository.findAllWithUserOrderByTotalScoreDesc()
                .stream()
                .map(UserScoreResponseDto::from)
                .toList();
    }

    /** 1위부터 끝까지: DENSE RANK (동점은 같은 등수) */
    @Transactional(readOnly = true)
    public List<UserRankingResponseDto> getAllRankingsSequential() {
        List<UserScore> list = userScoreRepository.findAllWithUserOrderByTotalScoreDesc();

        List<UserRankingResponseDto> result = new ArrayList<>(list.size());
        Integer prevScore = null;
        int rank = 0; // dense-rank
        for (UserScore us : list) {
            if (prevScore == null || us.getTotalScore() != prevScore) {
                rank++;                 // 점수가 바뀔 때만 등수 증가
                prevScore = us.getTotalScore();
            }
            result.add(UserRankingResponseDto.of(us, rank));
        }
        return result;
    }

    /** 내 랭킹: 내 점수보다 높은 인원 수 + 1  (동점이면 같은 등수) */
    @Transactional(readOnly = true)
    public UserRankingResponseDto getMyRankingSequential(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("유저 없음"));

        UserScore myScore = userScoreRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("점수 데이터 없음"));

        long higher = userScoreRepository.countByTotalScoreGreaterThan(myScore.getTotalScore());
        int myRank = (int) higher + 1;

        return UserRankingResponseDto.of(myScore, myRank);
    }

    /** 기존 점수가 없으면 새로 만들어 반환 */
    private UserScore findOrCreate(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("유저 없음"));

        return userScoreRepository.findByUser(user)
                .orElseGet(() -> userScoreRepository.save(new UserScore(user, 0, 0, 0)));
    }
}
