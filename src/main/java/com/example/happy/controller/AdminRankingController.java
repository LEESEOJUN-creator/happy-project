package com.example.happy.controller;

import com.example.happy.domain.UserScore;
import com.example.happy.service.AdminRankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/rankings")
public class AdminRankingController {

    private final AdminRankingService rankingService;

    @GetMapping("/top10")
    public ResponseEntity<List<UserScore>> getTop10() {
        return ResponseEntity.ok(rankingService.getTop10Rankings());
    }

    @PostMapping("/{userId}/reward")
    public ResponseEntity<Void> reward(@PathVariable Long userId) {
        rankingService.rewardUser(userId);
        return ResponseEntity.ok().build();
    }
}
