package com.project.toyProject.dao;

import com.project.toyProject.domain.dto.post.PostDetail;
import com.project.toyProject.domain.dto.post.PostListDTO;
import com.project.toyProject.domain.vo.PostVO;
import com.project.toyProject.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostDAO {

    private final PostMapper postMapper;

    public void insertPost(PostVO postVO) {
        postMapper.insertPost(postVO);
    }

    public List<String> selectPostFiles(Long postId){
        return  postMapper.selectPostFiles(postId);
    }

    public PostDetail selectPostDetail(Long postId) {
        return postMapper.selectPostDetail(postId);
    }

    public List<PostListDTO> selectPostList(Long limit, Long offset){
        return postMapper.selectPostList(limit, offset);
    }

    public Long countPost(){
        return postMapper.countPost();
    }

    public void countReadPost(Long postId){
        postMapper.countReadPost(postId);
    }


}
