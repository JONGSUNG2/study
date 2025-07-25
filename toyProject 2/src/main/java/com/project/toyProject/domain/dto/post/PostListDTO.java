package com.project.toyProject.domain.dto.post;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostListDTO {
    Long id;
    String postTitle;
    Long postReadCount;
    LocalDateTime postRegisterDate;
    LocalDateTime postUpdateDate;

    String memberName;

}
