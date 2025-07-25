package com.project.toyProject.mapper;

import com.project.toyProject.domain.vo.ProfileVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Profile;

@Mapper
public interface ProfileMapper {
    public void insertProfile(ProfileVO profileVO);
}
