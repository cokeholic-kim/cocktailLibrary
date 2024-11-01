package org.cocktail.cocktailappapi.domain.comment.controller;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.cocktail.cocktailappapi.domain.comment.business.CommentBusiness;
import org.cocktail.cocktailappapi.domain.comment.model.CommentRequest;
import org.cocktail.cocktailappapi.domain.comment.model.CommentResponse;
import org.cocktail.common.api.Api;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentBusiness commentBusiness;

    @PostMapping("save")
    public Api<String> saveComment(CommentRequest comment) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        commentBusiness.saveComment(comment, userEmail);
        return Api.OK("댓글이 저장되었습니다.");
    }

    @GetMapping("get/{category}/{item}")
    public Api<List<CommentResponse>> getComments(
            @PathVariable("category") String category,
            @PathVariable("item") String item
    ) {
        List<CommentResponse> commentResponses = commentBusiness.getCommentsList(category, item);
        return Api.OK(commentResponses);
    }

    @DeleteMapping("delete")
    public Api<String> deleteComment(@RequestBody Map<String, Long> request) {
        Long commentId = request.get("commentId");
        /*
        * 1. 댓글을 조회
        * 2. 유저를 조회
        * 3. 해당댓글이 조회된유저가 작성한게 맞다면 삭제 또는 관리자 계정이면 삭제가능.
        * 4. 아닌경우 에러를 발생 (작성한 댓글만 삭제할수있습니다.)
        * */
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        commentBusiness.deleteComment(commentId,userEmail);
        return Api.OK("ok");
    }
}
