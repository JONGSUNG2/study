package com.project.toyProject.service.member;

import com.project.toyProject.dao.FileDAO;
import com.project.toyProject.dao.MemberDAO;
import com.project.toyProject.dao.ProfileDAO;
import com.project.toyProject.domain.dto.member.MemberLoginDTO;
import com.project.toyProject.domain.dto.member.MemberProfileDTO;
import com.project.toyProject.domain.dto.member.ProfileUpdateDTO;
import com.project.toyProject.domain.vo.FileVO;
import com.project.toyProject.domain.vo.MemberVO;
import com.project.toyProject.domain.vo.ProfileVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final MemberDAO memberDAO;
    private final ProfileDAO profileDAO;
    private final FileDAO fileDAO;

    @Override
    public void join(MemberVO memberVO) {
        memberDAO.insertMember(memberVO);
        Long foundPK = memberDAO.selectLoginMemberPk(memberVO.getMemberLoginId(),memberVO.getMemberPassword());
        initProfile(foundPK);
    }

    @Override
    public Optional<MemberVO> login(MemberLoginDTO memberLoginDTO) {

        Long foundPK = memberDAO.selectLoginMemberPk(memberLoginDTO.getMemberLoginId(),memberLoginDTO.getMemberPassword());
        if(foundPK != null){
//            여기서 찾아온 pk로 memberVO 객체 받아온다음 리턴
            return memberDAO.selectLoginMember(foundPK);
        }
        return Optional.empty();
    }

    @Override
    public MemberVO findMemberById(Long id) {
        return memberDAO.selectLoginMember(id).orElse(null);
    }

    @Override
    public MemberProfileDTO findMemberProfileById(Long id) {
        return memberDAO.selectLoginMemberProfile(id);
    }

    @Override
    public ProfileUpdateDTO findProfileUpdateById(Long id){return memberDAO.selectProfileUpdate(id);}


    public void initProfile(Long memberId){
//        기본 프로필 한줄소개 설정
        ProfileVO profileVO = new ProfileVO();
        profileVO.setProfileOneLineBio("");
        profileVO.setMemberId(memberId);

//         기본 프로필 이미지 설정
        String filePath = "/Users/jongsung/Desktop/study/files/profile";
        File dir = new File(filePath);
        File imageFile = new File("/Users/jongsung/Desktop/study/files/default/DefaultImage.jpg");
        if (!dir.exists()){
            dir.mkdirs();
        }
        File targetFile = new File(dir, imageFile.getName());
        try {
            // 이미지 복사
            Files.copy(imageFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("이미지가 성공적으로 복사되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileVO fileVO = new FileVO();
        fileVO.setFileOriginalName("DefaultImage.jpg");
        String savedName = "DefaultImage.jpg";
        fileVO.setFileSavedName(savedName);
        fileVO.setFilePath(savedName); // <== 경로 말고 파일명만 저장
        fileVO.setFileType("PROFILE");
        fileVO.setReferenceId(memberId);

        fileDAO.insertFile(fileVO);
        profileDAO.insertProfile(profileVO);
    }

    @Override
    public void updateProfile(ProfileUpdateDTO profileUpdateDTO, Long memberId, MultipartFile file) {
        //MemberVO 업데이트
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberLoginId(profileUpdateDTO.getMemberLoginId());
        memberVO.setMemberName(profileUpdateDTO.getMemberName());
        memberVO.setMemberPhone(profileUpdateDTO.getMemberPhone());
        memberVO.setMemberBirth(String.valueOf(profileUpdateDTO.getMemberBirth()));
        memberVO.setMemberEmail(profileUpdateDTO.getMemberEmail());


        //ProfileVO 업데이트
        ProfileVO profileVO = new ProfileVO();
        profileVO.setProfileOneLineBio(profileUpdateDTO.getProfileOneLineBio());


        //FileVO 업데이트
        if (file != null && file.isEmpty() == false) {
            FileVO fileVO = new FileVO();
            fileVO.setFileOriginalName(file.getOriginalFilename());
            fileVO.setFileSavedName(UUID.randomUUID() + "_" + file.getOriginalFilename());
            fileVO.setFilePath("/Users/jongsung/Desktop/study/files/profile/" + fileVO.getFileSavedName());
            try {
                file.transferTo(new File(fileVO.getFilePath()));
            } catch (IOException e) {
                throw new RuntimeException("파일 저장 실패", e);
            }
            log.info(fileVO.toString());

            memberDAO.updateFile(fileVO, profileUpdateDTO.getFileId());
        }




        log.info("====================={}", memberVO.getMemberLoginId());
        memberDAO.updateMember(memberVO, memberId);

        memberDAO.updateProfile(profileVO, profileUpdateDTO.getProfileId());


    }


}
