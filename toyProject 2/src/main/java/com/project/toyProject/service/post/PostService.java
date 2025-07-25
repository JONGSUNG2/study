package com.project.toyProject.service.post;

import com.project.toyProject.domain.dto.post.PostDetail;
import com.project.toyProject.domain.dto.post.PostListDTO;
import com.project.toyProject.domain.dto.post.PostUpdateDTO;
import com.project.toyProject.domain.vo.PostVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface PostService {

    void insertPost(PostVO post, List<MultipartFile> files);

    List<PostListDTO> selectPostList(Long pageNumber);

    Long totalPage();

    PostDetail selectPostDetail(Long postId, String from);

    PostUpdateDTO selectPostUpdateDTO(Long postId);

    void updatePost(String title, String postContent, List<MultipartFile> files, Long postId, String deletedImages);

    void postLike(Long postId);

}
