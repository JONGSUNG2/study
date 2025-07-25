package com.project.toyProject.service.comment;

import com.project.toyProject.domain.dto.comment.PostCommentDTO;
import com.project.toyProject.domain.vo.CommentVO;

import java.util.List;

public interface CommentService {

    PostCommentDTO insertComment(Long postId, String content, Long memberId);

    List<PostCommentDTO> selectCommentsByPostId(Long postId);
}
