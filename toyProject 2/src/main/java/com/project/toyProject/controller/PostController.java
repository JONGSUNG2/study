package com.project.toyProject.controller;

import com.project.toyProject.domain.dto.post.PostDetail;
import com.project.toyProject.domain.dto.post.PostListDTO;
import com.project.toyProject.domain.dto.post.PostUpdateDTO;
import com.project.toyProject.domain.vo.PostVO;
import com.project.toyProject.service.post.PostService;
import com.project.toyProject.service.postLike.PostLikeService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/post/*")
@Slf4j
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/postList/{pageNum}")
    public String postList(Model model, @PathVariable("pageNum") Long pageNumber) {
        List<PostListDTO> postLists = postService.selectPostList(pageNumber);
        model.addAttribute("postLists", postLists);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", postService.totalPage());

        return "/post/postList";
    }

    @GetMapping("/postForm")
    public String saveForm(Model model) {

        model.addAttribute("post", new PostVO());
        return "/post/postForm";
    }

    @PostMapping("/postForm")
    public String savePost(PostVO post, @RequestParam(value = "files", required = false) List<MultipartFile> files, HttpSession session) {
        post.setMemberId((Long) session.getAttribute("sessionId"));
        postService.insertPost(post, files);

        return "redirect:/post/postDetail/" + post.getId();
    }

    @GetMapping("/update/{postId}")
    public String update(@PathVariable("postId") Long postId ,Model model){
        PostUpdateDTO postUpdateDTO = postService.selectPostUpdateDTO(postId);
        model.addAttribute("postUpdateDTO", postUpdateDTO);
        model.addAttribute("postId", postId);
        return "/post/postEdit";
    }

    @PostMapping("/update/{postId}")
    public String update(@RequestParam(value = "postTitle") String title, @RequestParam(value = "postContent")String postContent, @PathVariable("postId") Long postId,
                         @RequestParam(value = "files", required = false) List<MultipartFile> files,
                         @RequestParam(value = "deletedImages",required = false) String deletedImagesJson){

        postService.updatePost(title, postContent, files, postId, deletedImagesJson);
        return "redirect:/post/postDetail/" + postId;
    }

    @GetMapping("/postDetail/{postId}")
    public String postDetail(@RequestParam(value = "from", required = false) String from ,@PathVariable("postId") Long postId, Model model, HttpSession session) {
        Long sessionId = (Long) session.getAttribute("sessionId");
        PostDetail postDetail= postService.selectPostDetail(postId, from);
        log.info(postDetail.toString());
        model.addAttribute("sessionId", sessionId);
        model.addAttribute("postDetail", postDetail);
        model.addAttribute("postId", postId);
        return "/post/postDetail";
    }


}
