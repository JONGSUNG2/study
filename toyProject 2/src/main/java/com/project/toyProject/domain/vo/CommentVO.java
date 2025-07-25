package com.project.toyProject.domain.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentVO {

    //데베에 테이블 만들어야함
    private Long id;
    private Long memberId;
    private Long postId;

    private String content;
    private LocalDateTime createDate;
}
