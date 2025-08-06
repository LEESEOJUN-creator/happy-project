package com.example.happy.controller;

import com.example.happy.domain.User;
import com.example.happy.dto.response.UserRankingResponseDto;
import com.example.happy.dto.response.UserScoreResponseDto;
import com.example.happy.service.UserScoreService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/score")
public class UserScoreController {

    private final UserScoreService userScoreService;

    /* ───────────── 1) 게임 점수 반영 ───────────── */
    @PostMapping("/game")
    public ResponseEntity<Void> updateGameScore(
            @RequestParam int game1,
            @RequestParam int game2,
            HttpSession session) {

        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        userScoreService.updateGameScore(loginUser.getId(), game1, game2);
        return ResponseEntity.ok().build();
    }

    /* ───────────── 2) 게시글 작성 점수 반영 ───────────── */
    @PostMapping("/post")
    public ResponseEntity<Void> addPostScore(HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        userScoreService.addPostScore(loginUser.getId());
        return ResponseEntity.ok().build();
    }

    /* ───────────── 3) 점수 목록(정렬만, 기존 형태 유지) ───────────── */
    @GetMapping("/ranking")
    public ResponseEntity<List<UserScoreResponseDto>> getRankings() {
        return ResponseEntity.ok(userScoreService.getRankings());
    }

    /* ───────────── 4) 전체 랭킹(순위 포함) ───────────── */
    @GetMapping("/ranking/all")
    public ResponseEntity<List<UserRankingResponseDto>> getAllRankingsSequential() {
        return ResponseEntity.ok(userScoreService.getAllRankingsSequential());
    }

    /* ───────────── 5) 내 랭킹(순위 포함) ───────────── */
    @GetMapping("/ranking/me")
    public ResponseEntity<UserRankingResponseDto> getMyRanking(HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(userScoreService.getMyRankingSequential(loginUser.getId()));
    }
}
