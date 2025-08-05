package com.example.happy.service;

import com.example.happy.domain.Post;
import com.example.happy.domain.User;
import com.example.happy.dto.request.PostRequestDto;
import com.example.happy.dto.response.PostResponseDto;
import com.example.happy.repository.PostRepository;
import com.example.happy.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    /* ───────────── 1) 글 작성 ───────────── */
    @Transactional
    public Long createPost(Long authorId, PostRequestDto dto, MultipartFile imageFile)
            throws IOException {

        // 작성자 엔티티 조회
        User author = userRepository.findById(authorId)
                .orElseThrow(() ->
                        new EntityNotFoundException("작성자를 찾을 수 없습니다. id=" + authorId));

        // 이미지 저장 (선택)
        String imageUrl = null;
        if (imageFile != null && !imageFile.isEmpty()) {
            String dir = "uploads/";
            String fileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();

            Path savePath = Paths.get(dir, fileName);
            Files.createDirectories(savePath.getParent());
            Files.write(savePath, imageFile.getBytes());

            imageUrl = "/" + savePath.toString().replace("\\", "/");
        }

        Post post = Post.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .author(author)                 // FK 세팅
                .imageUrl(imageUrl)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        postRepository.save(post);
        return post.getPostId();
    }

    /* ───────────── 2) 전체 목록 ───────────── */
    @Transactional(readOnly = true)
    public List<PostResponseDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(PostResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    /* ───────────── 3) 단건 조회 ───────────── */
    @Transactional(readOnly = true)
    public PostResponseDto getPost(Long postId) {
        Post post = findPostOrThrow(postId);
        return PostResponseDto.fromEntity(post);
    }

    /* ───────────── 4) 수정 (본인만) ───────────── */
    @Transactional
    public Long updatePost(Long postId, PostRequestDto dto, Long authorId) {

        Post post = findPostOrThrow(postId);
        validateOwner(post, authorId);

        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setUpdatedAt(LocalDateTime.now());

        return post.getPostId();
    }

    /* ───────────── 5) 삭제 (본인만) ───────────── */
    @Transactional
    public void deletePost(Long postId, Long authorId) {

        Post post = findPostOrThrow(postId);
        validateOwner(post, authorId);

        postRepository.delete(post);
    }

    /* ======== 헬퍼 ======== */

    private Post findPostOrThrow(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() ->
                        new EntityNotFoundException("게시글을 찾을 수 없습니다. id=" + postId));
    }

    private void validateOwner(Post post, Long authorId) {
        if (!post.getAuthor().getId().equals(authorId)) {
            throw new IllegalStateException("작성자만 수정/삭제할 수 있습니다.");
        }
    }
}
