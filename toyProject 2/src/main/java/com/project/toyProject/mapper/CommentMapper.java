package com.project.toyProject.mapper;

import com.project.toyProject.domain.dto.comment.PostCommentDTO;
import com.project.toyProject.domain.vo.CommentVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    void insertComment(CommentVO commentVO);

    List<PostCommentDTO> selectCommentsByPostId(Long postId);
}
