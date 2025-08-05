package com.example.happy.controller;

import com.example.happy.domain.User;
import com.example.happy.dto.UserScoreResponseDto;
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
        if (loginUser == null) {               // 로그인 안 된 경우
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

    /* ───────────── 3) 랭킹 조회 (전체 공개) ───────────── */
    @GetMapping("/ranking")
    public ResponseEntity<List<UserScoreResponseDto>> getRankings() {
        return ResponseEntity.ok(userScoreService.getRankings());
    }
}
