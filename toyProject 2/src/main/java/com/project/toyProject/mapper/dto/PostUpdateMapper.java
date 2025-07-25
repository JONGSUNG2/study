package com.project.toyProject.mapper.dto;

import com.project.toyProject.domain.dto.post.PostUpdateDTO;
import com.project.toyProject.domain.vo.FileVO;
import com.project.toyProject.domain.vo.PostVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostUpdateMapper {

    public PostUpdateDTO selectUpdatePost(Long postId);

    public void updatePost(PostVO postVO);

    public void updateFile(FileVO fileVO);
}
