package com.project.toyProject.domain.dto.comment;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostCommentDTO {

    private String memberName;
    private String content;
    private LocalDateTime createdTime;
}
