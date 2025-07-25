package com.project.toyProject.controller;

import com.project.toyProject.service.postLike.PostLikeService;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/postLike")
@RequiredArgsConstructor
@Slf4j
public class PostLikeController {

    private final PostLikeService postLikeService;

    @PostMapping
    @ResponseBody
    public Map<String, Object> postLike(@RequestParam(value = "postId") Long postId, HttpSession session){
        Long memberId = (Long) session.getAttribute("sessionId");
        Long likeCount = postLikeService.insertPostLike(postId, memberId);
        return Map.of("likeCount", likeCount);
    }

    @GetMapping("/countLike/{postId}")
    @ResponseBody
    public Map<String, Object> countLike(@PathVariable(value = "postId") Long postId){
        Long likeCount = postLikeService.countPostLike(postId);
        return Map.of("likeCount", likeCount);
    }

}
