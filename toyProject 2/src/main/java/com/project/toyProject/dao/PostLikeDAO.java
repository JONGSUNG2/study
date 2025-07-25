package com.project.toyProject.dao;

import com.project.toyProject.domain.vo.PostLikeVO;
import com.project.toyProject.mapper.PostLikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostLikeDAO {
    private final PostLikeMapper postLikeMapper;

    public void insertPostLike(Long postId, Long memberId){
        postLikeMapper.insertPostLike(postId, memberId);
    }

    public PostLikeVO selectPostLike(Long postId, Long memberId){
        return postLikeMapper.selectPostLike(postId, memberId);
    }

    public void deletePostLike(Long postId, Long memberId){
        postLikeMapper.deletePostLike(postId, memberId);
    }

    public Long countPostLike(Long postId){
        return postLikeMapper.countPostLike(postId);
    }
}
