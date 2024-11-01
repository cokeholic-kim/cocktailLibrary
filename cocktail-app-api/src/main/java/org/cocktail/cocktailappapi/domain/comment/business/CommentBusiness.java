package org.cocktail.cocktailappapi.domain.comment.business;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cocktail.cocktailappapi.domain.comment.converter.CommentConverter;
import org.cocktail.cocktailappapi.domain.comment.model.CommentRequest;
import org.cocktail.cocktailappapi.domain.comment.model.CommentResponse;
import org.cocktail.cocktailappapi.domain.comment.service.CommentService;
import org.cocktail.cocktailappapi.domain.comment.validate.ErrorCodeComment;
import org.cocktail.common.exception.ApiException;
import org.cocktail.db.comment.CommentEntity;
import org.cocktail.db.user.UserEntity;
import org.cocktail.db.user.enums.UserRole;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentBusiness {

    private final CommentConverter commentConverter;
    private final CommentService commentService;

    public void saveComment(CommentRequest comment, String userEmail) {
        CommentEntity commentEntity = commentConverter.toEntity(comment);
        commentService.commentEntitySave(commentEntity, userEmail, comment.getParentCommentId());
    }

    public List<CommentResponse> getCommentsList(String category, String item) {
        List<CommentEntity> commentEntityList = commentService.getComments(category, item);
        return commentEntityList.stream().map(commentConverter::toResponse).toList();
    }

    public void deleteComment(Long commentId, String userEmail) {
        CommentEntity forDeleteComment = commentService.getComment(commentId);
        UserEntity requestUser = commentService.findUser(userEmail);
        if(isEqualCommentUser(requestUser,forDeleteComment) || isAdmin(requestUser)){
            forDeleteComment.setDeleted(true);
            commentService.saveCommentEntity(forDeleteComment);
        }else{
            throw new ApiException(ErrorCodeComment.FAIL_DELETE);
        };
    }

    private static boolean isAdmin(UserEntity user) {
        return user.getRole() == UserRole.ROLE_ADMIN;
    }

    private static boolean isEqualCommentUser(UserEntity user, CommentEntity comment) {
        return comment.getUser().equals(user);
    }
}
