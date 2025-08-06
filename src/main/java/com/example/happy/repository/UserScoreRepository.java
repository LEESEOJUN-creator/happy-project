package com.example.happy.repository;

import com.example.happy.domain.User;
import com.example.happy.domain.UserScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserScoreRepository extends JpaRepository<UserScore, Long> {
    Optional<UserScore> findByUser(User user);
    List<UserScore> findAllByOrderByTotalScoreDesc();
    @Query("select us from UserScore us join fetch us.user order by us.totalScore desc")
    List<UserScore> findAllWithUserOrderByTotalScoreDesc();

    // 내 점수보다 높은 사람 수
    long countByTotalScoreGreaterThan(int totalScore);
}


