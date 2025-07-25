package com.project.toyProject.service.postLike;

import com.project.toyProject.dao.PostLikeDAO;
import com.project.toyProject.domain.vo.PostLikeVO;
import com.project.toyProject.domain.vo.PostVO;
import com.project.toyProject.mapper.PostLikeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostLikeServiceImpl implements PostLikeService{
    private final PostLikeDAO postLikeDAO;


    @Override
    public Long insertPostLike(Long postId, Long memberId) {
        PostLikeVO postLikeVO = postLikeDAO.selectPostLike(postId, memberId);

        if (postLikeVO != null){
            postLikeDAO.deletePostLike(postId, memberId);
        }else{
            postLikeDAO.insertPostLike(postId, memberId);
        }

        return postLikeDAO.countPostLike(postId);
    }

    @Override
    public Long countPostLike(Long postId) {
        return postLikeDAO.countPostLike(postId);
    }
}
