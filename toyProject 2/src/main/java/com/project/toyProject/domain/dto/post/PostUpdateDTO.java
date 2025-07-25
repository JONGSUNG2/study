package com.project.toyProject.domain.dto.post;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostUpdateDTO {

    private String postTitle;
    private String postContent;
    private LocalDateTime postUpdateDate;
    private Long memberId;

    private List<Long> fileIds;
    private List<String> fileSavedNames;
}
