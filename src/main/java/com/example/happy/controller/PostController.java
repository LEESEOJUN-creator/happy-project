package com.example.happy.controller;

import com.example.happy.dto.PostRequestDto;
import com.example.happy.dto.PostResponseDto;
import com.example.happy.service.PostService;
import lombok.RequiredArgsConstructor;
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

    // 게시글 생성
    @PostMapping
    public ResponseEntity<Long> createPost(
            @RequestParam Long userId,
            @RequestPart PostRequestDto dto,
            @RequestPart(required = false) MultipartFile imageFile) throws IOException {
        Long postId = postService.createPost(userId, dto, imageFile);
        return ResponseEntity.ok(postId);
    }

    // 게시글 목록 조회
    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getAllPosts() {
        List<PostResponseDto> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    // 게시글 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long id) {
        PostResponseDto post = postService.getPost(id);
        return ResponseEntity.ok(post);
    }

    // 게시글 수정
    @PutMapping("/{id}")
    public ResponseEntity<Long> updatePost(
            @PathVariable Long id,
            @RequestBody PostRequestDto dto) {
        Long updatedId = postService.updatePost(id, dto);
        return ResponseEntity.ok(updatedId);
    }

    // 게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
