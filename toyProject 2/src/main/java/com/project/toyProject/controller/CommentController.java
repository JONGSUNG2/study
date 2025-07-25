package com.project.toyProject.controller;

import com.project.toyProject.domain.dto.comment.PostCommentDTO;
import com.project.toyProject.service.comment.CommentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
@Slf4j
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/list/{postId}")
    @ResponseBody
    public List<PostCommentDTO> Comments(@PathVariable Long postId){
        List<PostCommentDTO> comments = commentService.selectCommentsByPostId(postId);
//        log.info("postCommentDTO = {}", comments);
        return comments;
    }

    @PostMapping("/add")
    @ResponseBody
    public PostCommentDTO addComment(@RequestParam Long postId, @RequestParam String content, HttpSession session){
        Long memberId = (Long) session.getAttribute("sessionId");
        PostCommentDTO postCommentDTO = commentService.insertComment(postId, content, memberId);

        return postCommentDTO;
    }


}
