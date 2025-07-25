package com.project.toyProject.service.comment;

import com.project.toyProject.dao.CommentDAO;
import com.project.toyProject.dao.MemberDAO;
import com.project.toyProject.domain.dto.comment.PostCommentDTO;
import com.project.toyProject.domain.vo.CommentVO;
import com.project.toyProject.domain.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService{
    private final CommentDAO commentDAO;
    private final MemberDAO memberDAO;

    @Override
    public PostCommentDTO insertComment(Long postId, String content, Long memberId) {
        CommentVO commentVO = new CommentVO();
        PostCommentDTO commentDTO = new PostCommentDTO();

        //댓글저장
        commentVO.setPostId(postId);
        commentVO.setContent(content);
        commentVO.setMemberId(memberId);
        LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        commentVO.setCreateDate(now);
        commentDAO.insertComment(commentVO);

        //저장된 댓글 보여주기

        commentDTO.setContent(content);
        commentDTO.setCreatedTime(now);
        Optional<MemberVO> memberVO = memberDAO.selectLoginMember(memberId);

        if (memberVO.isPresent()){
            String memberName = memberVO.get().getMemberName();
            commentDTO.setMemberName(memberName);
        }else {
            commentDTO.setMemberName("");
        }

        return commentDTO;

    }

    @Override
    public List<PostCommentDTO> selectCommentsByPostId(Long postId) {
        List<PostCommentDTO> comments = commentDAO.selectCommentsByPostId(postId);
        log.info("postCommentDTO = {}", comments);
        if (comments==null || comments.isEmpty()){
            comments = new ArrayList<>();
        }
        return comments;
    }
}
