package com.project.toyProject.mapper;

import com.project.toyProject.domain.vo.PostLikeVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostLikeMapper {
    void insertPostLike(Long postId, Long memberId);
    PostLikeVO selectPostLike(Long postId, Long memberId);
    void deletePostLike(Long postId, Long memberId);
    Long countPostLike(Long postId);
}
