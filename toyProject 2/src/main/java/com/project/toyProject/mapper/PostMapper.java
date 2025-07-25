package com.project.toyProject.mapper;

import com.project.toyProject.domain.dto.post.PostDetail;
import com.project.toyProject.domain.dto.post.PostListDTO;
import com.project.toyProject.domain.vo.PostVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
        void insertPost(PostVO post);
        PostDetail selectPostDetail(Long postId);
        List<String> selectPostFiles(Long postId);

        List<PostListDTO> selectPostList(Long limit, Long offset);

        Long countPost();

        void countReadPost(Long postId);
}
