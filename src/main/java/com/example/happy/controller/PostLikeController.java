package com.example.happy.controller;

import com.example.happy.domain.User;
import com.example.happy.service.PostLikeService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/{postId}/likes")
public class PostLikeController {

    private final PostLikeService postLikeService;

    /* ───────────── 1) 좋아요 ───────────── */
    @PostMapping
    public ResponseEntity<String> likePost(@PathVariable Long postId,
                                           HttpSession  session) {

        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 필요");
        }

        postLikeService.likePost(loginUser.getId(), postId);
        return ResponseEntity.ok("Liked");
    }

    /* ───────────── 2) 좋아요 취소 ───────────── */
    @DeleteMapping
    public ResponseEntity<String> unlikePost(@PathVariable Long postId,
                                             HttpSession  session) {

        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 필요");
        }

        postLikeService.unlikePost(loginUser.getId(), postId);
        return ResponseEntity.ok("Unliked");
    }

    /* ───────────── 3) 좋아요 개수 조회 ───────────── */
    @GetMapping("/count")
    public ResponseEntity<Long> countLikes(@PathVariable Long postId) {
        return ResponseEntity.ok(postLikeService.countLikes(postId));
    }
}
