package com.project.toyProject.service.post;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.toyProject.dao.FileDAO;
import com.project.toyProject.dao.PostDAO;
import com.project.toyProject.domain.dto.post.PostDetail;
import com.project.toyProject.domain.dto.post.PostListDTO;
import com.project.toyProject.domain.dto.post.PostUpdateDTO;
import com.project.toyProject.domain.vo.FileVO;
import com.project.toyProject.domain.vo.PostVO;

import com.project.toyProject.mapper.dto.PostUpdateMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostDAO postDAO;
    private final FileDAO fileDAO;
    private final PostUpdateMapper postUpdateMapper;

    @Override
    public void insertPost(PostVO post, List<MultipartFile> files) {
        post.setPostRegisterDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

        postDAO.insertPost(post);


        for(MultipartFile file : files){
            if(file != null && !file.isEmpty()){
                FileVO fileVO = new FileVO();
                Long postId = post.getId();
                makeFile(file, fileVO, postId);
                fileDAO.insertFile(fileVO);
            }
        }

    }

    @Override
    public List<PostListDTO> selectPostList(Long pageNumber) {
        Long limit = 3L;
        return postDAO.selectPostList(limit, limit*(pageNumber - 1));
    }

    @Override
    public Long totalPage() {
        Long totalPage = postDAO.countPost();

        totalPage = (long) Math.ceil((double) totalPage / 3);;
        if (totalPage == 0){
            totalPage = 1L;
        }
        return totalPage;
    }

    @Override
    public PostDetail selectPostDetail(Long postId, String from) {
        List<String> files = postDAO.selectPostFiles(postId);
        PostDetail postDetail = postDAO.selectPostDetail(postId);
        log.info(files.toString());
        postDetail.setFileSavedNames(files);

        if (from!=null && from.equals("list")){
            postDAO.countReadPost(postId);
            postDetail.setPostReadCount(postDetail.getPostReadCount() + 1);
        }

        return postDetail;
    }

    @Override
    public PostUpdateDTO selectPostUpdateDTO(Long postId) {

        PostUpdateDTO postUpdateDTO = postUpdateMapper.selectUpdatePost(postId);
        postUpdateDTO.setPostUpdateDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

        List<String> fileSavedNames = fileDAO.selectPostFiles(postId);

        postUpdateDTO.setFileSavedNames(fileSavedNames);
        return postUpdateDTO;
    }

    @Override
    public void updatePost(String title, String postContent, List<MultipartFile> files, Long postId, String deletedImagesJson) {
        //post 업데이트
        PostVO postVO = new PostVO();
        postVO.setId(postId);
        postVO.setPostTitle(title);
        postVO.setPostContent(postContent);
        postVO.setPostUpdateDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

        //file 업데이트

        //file 삭제
        if (deletedImagesJson != null && !deletedImagesJson.isEmpty()){
            ObjectMapper mapper = new ObjectMapper();
            List<String> deletedImages = null; try {
                deletedImages = mapper.readValue(deletedImagesJson, new TypeReference<>() {
                });

            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            for(String deletedImage : deletedImages){
                fileDAO.deletePostFile(deletedImage);
            }
        }


        //file 생성
        if (files != null && !files.isEmpty()) {
            for(MultipartFile file : files){
                FileVO fileVO = new FileVO();
                makeFile(file, fileVO, postId);
                fileDAO.insertFile(fileVO);
            }

        }

        postUpdateMapper.updatePost(postVO);
    }

    @Override
    public void postLike(Long postId) {

    }

    private static void makeFile(MultipartFile file, FileVO fileVO, Long postId) {
        UUID fileId = UUID.randomUUID();

        fileVO.setFileOriginalName(file.getOriginalFilename());
        fileVO.setFileSavedName(fileId+ file.getOriginalFilename());
        fileVO.setFilePath("/Users/jongsung/Desktop/study/files/post/"+ fileVO.getFileSavedName());
        fileVO.setFileType("POST");
        fileVO.setReferenceId(postId);

        try {
            file.transferTo(new File(fileVO.getFilePath()));
        } catch (IOException e) {
            throw new RuntimeException("파일 저장 실패", e);
        }
    }


}
