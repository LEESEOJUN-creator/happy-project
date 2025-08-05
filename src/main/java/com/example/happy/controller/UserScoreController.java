package com.example.happy.controller;

import com.example.happy.domain.UserScore;
import com.example.happy.dto.UserScoreResponseDto;
import com.example.happy.service.UserScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/score")
public class UserScoreController {

    private final UserScoreService userScoreService;

    @PostMapping("/game")
    public ResponseEntity<Void> updateGameScore(
            @RequestParam Long userId,
            @RequestParam int game1,
            @RequestParam int game2) {
        userScoreService.updateGameScore(userId, game1, game2);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/post")
    public ResponseEntity<Void> addPostScore(@RequestParam Long userId) {
        userScoreService.addPostScore(userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/ranking")
    public ResponseEntity<List<UserScoreResponseDto>> getRankings() {
        return ResponseEntity.ok(userScoreService.getRankings());
    }
}
