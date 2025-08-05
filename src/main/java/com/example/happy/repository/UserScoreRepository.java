package com.example.happy.repository;

import com.example.happy.domain.User;
import com.example.happy.domain.UserScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserScoreRepository extends JpaRepository<UserScore, Long> {
    Optional<UserScore> findByUser(User user);
    List<UserScore> findAllByOrderByTotalScoreDesc();
}
