package com.project.toyProject.service.postLike;

import com.project.toyProject.domain.vo.PostLikeVO;

public interface PostLikeService {

    Long insertPostLike(Long postId, Long memberId);

    Long countPostLike(Long postId);

}
