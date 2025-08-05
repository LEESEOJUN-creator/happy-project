package com.example.happy.controller;

import com.example.happy.domain.User;
import com.example.happy.dto.request.CommentRequestDto;
import com.example.happy.dto.response.CommentResponseDto;
import com.example.happy.service.CommentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/{postId}/comments")
public class CommentController {

    private final CommentService commentService;

    /* 1) 작성 */
    @PostMapping
    public ResponseEntity<Long> createComment(@PathVariable Long postId,
                                              @RequestBody  CommentRequestDto dto,
                                              HttpSession   session) {

        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Long id = commentService.createComment(postId, loginUser.getId(), dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    /* 2) 목록 */
    @GetMapping
    public ResponseEntity<List<CommentResponseDto>>
    getComments(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.getCommentsByPost(postId));
    }

    /* 3) 수정 */
    @PutMapping("/{commentId}")
    public ResponseEntity<Long> updateComment(@PathVariable Long postId,
                                              @PathVariable Long commentId,
                                              @RequestBody  CommentRequestDto dto,
                                              HttpSession   session) {

        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Long updated = commentService.updateComment(commentId, dto, loginUser.getId());
        return ResponseEntity.ok(updated);
    }

    /* 4) 삭제 */
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long postId,
                                              @PathVariable Long commentId,
                                              HttpSession   session) {

        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        commentService.deleteComment(commentId, loginUser.getId());
        return ResponseEntity.noContent().build();
    }
}
