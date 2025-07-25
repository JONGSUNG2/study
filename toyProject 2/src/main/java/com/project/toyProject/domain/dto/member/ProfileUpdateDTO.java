package com.project.toyProject.domain.dto.member;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProfileUpdateDTO {
    private String profileOneLineBio;

    private String fileType;
    private String fileSavedName;

    private String memberName;
    private String memberLoginId;
    private LocalDateTime memberBirth;
    private String memberEmail;
    private String memberPhone;


    private Long profileId;
    private Long fileId;


}
