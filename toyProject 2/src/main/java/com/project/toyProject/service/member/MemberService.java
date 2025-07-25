package com.project.toyProject.service.member;

import com.project.toyProject.domain.dto.member.MemberLoginDTO;
import com.project.toyProject.domain.dto.member.MemberProfileDTO;
import com.project.toyProject.domain.dto.member.ProfileUpdateDTO;
import com.project.toyProject.domain.vo.MemberVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface MemberService {
    public void join(MemberVO memberVO);
    public Optional<MemberVO> login(MemberLoginDTO memberLoginDTO);
    public MemberVO findMemberById(Long id);
    public MemberProfileDTO findMemberProfileById(Long id);

    ProfileUpdateDTO findProfileUpdateById(Long id);

    public void updateProfile(ProfileUpdateDTO profileUpdateDTO, Long id, MultipartFile file);
}
