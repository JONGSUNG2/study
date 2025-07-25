package com.project.toyProject.mapper;

import com.project.toyProject.domain.vo.FileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {
    public void insertFile(FileVO file);

    public List<String> selectPostFiles(Long postId);

    public void deletePostFile(String fileSavedName);
}
