package com.project.toyProject.dao;

import com.project.toyProject.domain.vo.ProfileVO;
import com.project.toyProject.mapper.ProfileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProfileDAO {
    private final ProfileMapper profileMapper;
    public void insertProfile(ProfileVO profileVO){
        profileMapper.insertProfile(profileVO);
    }

}
