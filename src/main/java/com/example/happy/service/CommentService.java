package com.example.happy.service;

import com.example.happy.domain.Comment;
import com.example.happy.domain.Post;
import com.example.happy.domain.User;
import com.example.happy.dto.request.CommentRequestDto;
import com.example.happy.dto.response.CommentResponseDto;
import com.example.happy.repository.CommentRepository;
import com.example.happy.repository.PostRepository;
import com.example.happy.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository    userRepository;
    private final PostRepository    postRepository;

    /* 1) 댓글 작성 ────────────────────────── */
    @Transactional
    public Long createComment(Long postId, Long authorId, CommentRequestDto dto) {

        Post  post  = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("게시글 없음"));
        User  user  = userRepository.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("유저 없음"));

        Comment comment = Comment.builder()
                .post(post)
                .author(user)
                .content(dto.getContent())
                .build();

        commentRepository.save(comment);
        return comment.getId();
    }

    /* 2) 목록 조회 ────────────────────────── */
    public List<CommentResponseDto> getCommentsByPost(Long postId) {
        return commentRepository.findByPost_PostId(postId).stream()
                .map(CommentResponseDto::fromEntity)
                .toList();
    }

    /* 3) 수정 ─────────────────────────────── */
    @Transactional
    public Long updateComment(Long commentId,
                              CommentRequestDto dto,
                              Long requesterId) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("댓글 없음"));

        // 작성자 본인인지 확인
        if (!comment.getAuthor().getId().equals(requesterId)) {
            throw new AccessDeniedException("본인만 수정 가능합니다.");
        }

        comment.setContent(dto.getContent());
        comment.setUpdatedAt(LocalDateTime.now());
        return comment.getId();
    }

    /* 4) 삭제 ─────────────────────────────── */
    @Transactional
    public void deleteComment(Long commentId, Long requesterId) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("댓글 없음"));

        if (!comment.getAuthor().getId().equals(requesterId)) {
            throw new AccessDeniedException("본인만 삭제 가능합니다.");
        }
        commentRepository.delete(comment);
    }
}
