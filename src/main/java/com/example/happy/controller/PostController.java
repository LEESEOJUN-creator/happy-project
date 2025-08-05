package com.example.happy.controller;

import com.example.happy.domain.User;
import com.example.happy.dto.request.PostRequestDto;
import com.example.happy.dto.response.PostResponseDto;
import com.example.happy.service.PostService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    /* ───────────── 1) 게시글 작성 ───────────── */
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Long> createPost(
            @ModelAttribute PostRequestDto dto,
            @RequestPart(required = false) MultipartFile imageFile,
            HttpSession session) throws IOException {

        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Long postId = postService.createPost(loginUser.getId(), dto, imageFile);
        return ResponseEntity.status(HttpStatus.CREATED).body(postId);
    }

    /* ───────────── 2) 전체 목록 ───────────── */
    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    /* ───────────── 3) 단건 조회 ───────────── */
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.getPost(postId));
    }

    /* ───────────── 4) 수정 ───────────── */
    @PutMapping("/{postId}")
    public ResponseEntity<Long> updatePost(
            @PathVariable Long postId,
            @RequestBody PostRequestDto dto,
            HttpSession session) {

        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Long updatedId = postService.updatePost(postId, dto, loginUser.getId());
        return ResponseEntity.ok(updatedId);
    }

    /* ───────────── 5) 삭제 ───────────── */
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(
            @PathVariable Long postId,
            HttpSession session) {

        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        postService.deletePost(postId, loginUser.getId());
        return ResponseEntity.noContent().build();
    }
}
