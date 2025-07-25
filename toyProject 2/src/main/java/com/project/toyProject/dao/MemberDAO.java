package com.project.toyProject.dao;

import com.project.toyProject.domain.dto.member.MemberProfileDTO;
import com.project.toyProject.domain.dto.member.ProfileUpdateDTO;
import com.project.toyProject.domain.vo.FileVO;
import com.project.toyProject.domain.vo.MemberVO;
import com.project.toyProject.domain.vo.ProfileVO;
import com.project.toyProject.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MemberDAO {
    private final MemberMapper memberMapper;

    public void insertMember(MemberVO memberVO) {
        memberMapper.insertMember(memberVO);
    }
    public Long selectLoginMemberPk(String memberLoginId,String memberPassword) {
        return memberMapper.selectLoginMemberPK(memberLoginId,memberPassword);
    }
    public Optional<MemberVO> selectLoginMember(Long id) {
        return memberMapper.selectLoginMember(id);
    }
    public MemberProfileDTO selectLoginMemberProfile(Long id) {
        return memberMapper.selectMemberProfile(id);
    }
    public ProfileUpdateDTO selectProfileUpdate(Long id) {return memberMapper.selectProfileUpdate(id);}
    public void updateMember(MemberVO memberVO, Long id) {

        memberMapper.updateMember(memberVO, id);
    }
    public void updateProfile(ProfileVO profileVO, Long id) { memberMapper.updateProfile(profileVO, id);}
    public void updateFile(FileVO fileVO, Long id) { memberMapper.updateFile(fileVO, id);}

}
