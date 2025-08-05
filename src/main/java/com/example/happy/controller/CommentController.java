package com.example.happy.controller;

import com.example.happy.dto.request.CommentRequestDto;
import com.example.happy.dto.response.CommentResponseDto;
import com.example.happy.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/{postId}/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Long> createComment(
            @PathVariable Long postId,
            @RequestParam Long userId,
            @RequestBody CommentRequestDto dto) {
        Long id = commentService.createComment(postId, userId, dto);
        return ResponseEntity.ok(id);
    }

    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> getComments(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.getCommentsByPost(postId));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Long> updateComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @RequestBody CommentRequestDto dto) {
        return ResponseEntity.ok(commentService.updateComment(commentId, dto));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long postId,
            @PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}

