package com.project.toyProject.dao;

import com.project.toyProject.domain.dto.comment.PostCommentDTO;
import com.project.toyProject.domain.vo.CommentVO;
import com.project.toyProject.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentDAO {

    private final CommentMapper commentMapper;

    public void insertComment(CommentVO commentVO){
        commentMapper.insertComment(commentVO);
    }

    public List<PostCommentDTO> selectCommentsByPostId(Long postId){
        return commentMapper.selectCommentsByPostId(postId);
    }
}
