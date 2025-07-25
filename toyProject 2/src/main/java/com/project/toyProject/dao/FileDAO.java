package com.project.toyProject.dao;

import com.project.toyProject.domain.vo.FileVO;
import com.project.toyProject.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FileDAO {
    private final FileMapper fileMapper;

    public void insertFile(FileVO fileVO) {
            fileMapper.insertFile(fileVO);
    }

    public List<String> selectPostFiles(Long postId){
        return fileMapper.selectPostFiles(postId);
    }

    public void deletePostFile(String fileSavedName){
        fileMapper.deletePostFile(fileSavedName);
    }
}
