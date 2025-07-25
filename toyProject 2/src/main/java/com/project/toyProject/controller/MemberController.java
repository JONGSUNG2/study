package com.project.toyProject.controller;

import com.project.toyProject.domain.dto.member.MemberLoginDTO;
import com.project.toyProject.domain.dto.member.MemberProfileDTO;
import com.project.toyProject.domain.dto.member.ProfileUpdateDTO;
import com.project.toyProject.domain.vo.MemberVO;
import com.project.toyProject.service.member.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/member/*")
@Slf4j
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("member", new MemberVO());
        return "/member/join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute MemberVO memberVO) {
//        bindingresult 추가해야함, 검증도 해야함
        memberService.join(memberVO);

        log.info(memberVO.toString());
        return "redirect:/member/login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("memberLoginDTO", new MemberLoginDTO());
        return "/member/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute MemberLoginDTO memberLoginDTO, HttpSession session) {
        log.info(memberLoginDTO.toString());
//        bindingresult 추가해야함, 검증도 해야함
        Optional<MemberVO> loginMember = memberService.login(memberLoginDTO);

        if (loginMember != null && loginMember.isPresent()) {
            session.setAttribute("sessionId", loginMember.get().getId());
            return "redirect:/";
        }
        return "/member/login";
    }

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {
        Long sessionId = (Long) session.getAttribute("sessionId");
        MemberProfileDTO memberProfile = memberService.findMemberProfileById(sessionId);

        model.addAttribute("memberProfile", memberProfile);
        model.addAttribute("filePath", "/files/" + memberProfile.getFileSavedName());
        model.addAttribute("memberId", sessionId);
        return "/member/profile";
    }

    @GetMapping("/profile/edit/{memberId}")
    public String editProfile(@PathVariable("memberId") Long memberId, Model model) {
        ProfileUpdateDTO memberProfile = memberService.findProfileUpdateById(memberId);
        model.addAttribute("memberId", memberId);
        model.addAttribute("memberProfile", memberProfile);
        model.addAttribute("filePath", "/files/" + memberProfile.getFileSavedName());
        log.info(memberProfile.toString());
        return "/member/profileEdit";
    }

    @PostMapping("/profile/edit/{memberId}")
    public String editProfile( @RequestParam(value = "profileImage", required = false) MultipartFile profileImage, @ModelAttribute ProfileUpdateDTO profileUpdateDTO, @PathVariable("memberId") Long memberId) {
        log.info("파일 이름: {}", profileImage != null ? profileImage.getOriginalFilename() : "파일 없음");
        log.info(profileUpdateDTO.toString());
        memberService.updateProfile(profileUpdateDTO, memberId, profileImage);
        return "redirect:/member/profile";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }



    @PostMapping("/test/upload")
    public String testUpload(@RequestParam("profileImage") MultipartFile file) {
        System.out.println("파일 이름: " + file.getOriginalFilename());
        return "redirect:/";
    }
    @GetMapping("/test/upload")
    public String testUpload() {

        return "member/test";
    }


}
