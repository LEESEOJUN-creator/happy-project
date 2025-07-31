package com.example.happy.controller;

import com.example.happy.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/{postId}/likes")
public class PostLikeController {

    private final PostLikeService postLikeService;

    @PostMapping
    public ResponseEntity<String> likePost(@PathVariable Long postId, @RequestParam Long userId) {
        postLikeService.likePost(userId, postId);
        return ResponseEntity.ok("Liked");
    }

    @DeleteMapping
    public ResponseEntity<String> unlikePost(@PathVariable Long postId, @RequestParam Long userId) {
        postLikeService.unlikePost(userId, postId);
        return ResponseEntity.ok("Unliked");
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countLikes(@PathVariable Long postId) {
        return ResponseEntity.ok(postLikeService.countLikes(postId));
    }
}
