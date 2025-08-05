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
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public Long createComment(Long postId, Long userId, CommentRequestDto dto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("게시글 없음"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("유저 없음"));

        Comment parent = null;
        if (dto.getParentId() != null) {
            parent = commentRepository.findById(dto.getParentId())
                    .orElseThrow(() -> new EntityNotFoundException("부모 댓글 없음"));
        }

        Comment comment = Comment.builder()
                .post(post)
                .author(user)
                .content(dto.getContent())
                .parent(parent)
                .build();

        commentRepository.save(comment);
        return comment.getId();
    }

    @Transactional(readOnly = true)
    public List<CommentResponseDto> getCommentsByPost(Long postId) {
        return commentRepository.findByPost_PostId(postId).stream()
                .map(CommentResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long updateComment(Long commentId, CommentRequestDto dto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("댓글 없음"));
        comment.setContent(dto.getContent());
        comment.setUpdatedAt(LocalDateTime.now());
        return comment.getId();
    }

    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("댓글 없음"));
        commentRepository.delete(comment);
    }
}
