package com.project.toyProject.domain.dto.post;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostDetail {

    String postTitle;
    String memberName;
    LocalDateTime postRegisterDate;
    LocalDateTime postUpdateDate;

    String postContent;
    List<String> fileSavedNames;
    Long postReadCount;

}
