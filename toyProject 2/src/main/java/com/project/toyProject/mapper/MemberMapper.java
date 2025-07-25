package com.project.toyProject.mapper;

import com.project.toyProject.domain.dto.member.MemberProfileDTO;
import com.project.toyProject.domain.dto.member.ProfileUpdateDTO;
import com.project.toyProject.domain.vo.FileVO;
import com.project.toyProject.domain.vo.MemberVO;
import com.project.toyProject.domain.vo.ProfileVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface MemberMapper {

    public void insertMember(MemberVO memberVO);
    public Long selectLoginMemberPK(String memberLoginId, String memberPassword);
    public Optional<MemberVO> selectLoginMember(Long id);
    public MemberProfileDTO selectMemberProfile(Long id);
    public ProfileUpdateDTO selectProfileUpdate(Long id);
    public void updateMember(@Param("memberVO") MemberVO memberVO,@Param("id") Long id);
    public void updateProfile(@Param("profileVO") ProfileVO profileVO,@Param("id") Long id);
    public void updateFile(@Param("fileVO") FileVO fileVO,@Param("id") Long id);
}
