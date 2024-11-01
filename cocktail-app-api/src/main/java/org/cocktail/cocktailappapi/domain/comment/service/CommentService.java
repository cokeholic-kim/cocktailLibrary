package org.cocktail.cocktailappapi.domain.comment.service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.cocktail.cocktailappapi.domain.comment.validate.ErrorCodeComment;
import org.cocktail.cocktailappapi.domain.user.validate.ErrorCodeUser;
import org.cocktail.common.exception.ApiException;
import org.cocktail.db.comment.CommentEntity;
import org.cocktail.db.comment.CommentRepository;
import org.cocktail.db.comment.enums.CommentRefCategory;
import org.cocktail.db.user.UserEntity;
import org.cocktail.db.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Transactional
    public void commentEntitySave(CommentEntity commentEntity, String userEmail, Long parentCommentId) {
        UserEntity userEntity = findUser(userEmail);
        commentEntity.setUser(userEntity);

        if (isContainParentComment(parentCommentId)) {
            CommentEntity parentComment = findParentComment(parentCommentId);
            commentEntity.setParentComment(parentComment);
        }

        saveCommentEntity(commentEntity);
    }

    public void saveCommentEntity(CommentEntity commentEntity) {
        commentRepository.save(commentEntity);
    }


    private static boolean isContainParentComment(Long comment) {
        return comment != null;
    }

    public UserEntity findUser(String userEmail) {
        Optional<UserEntity> byEmail = userRepository.findByEmail(userEmail);
        return byEmail.orElseThrow(() -> new ApiException(ErrorCodeUser.NULL_USER));
    }


    private CommentEntity findParentComment(Long comment) {
        return commentRepository.findById(comment).orElseThrow(
                () -> new ApiException(ErrorCodeComment.NULL_COMMENT));
    }

    @Transactional
    public List<CommentEntity> getComments(String category, String item) {
        return commentRepository.findAllByRefCategoryAndRefCategoryItemAndParentCommentIsNullAndDeletedIsFalseOrderByCreatedAt(
                CommentRefCategory.valueOf(category), item);
    }

    @Transactional
    public CommentEntity getComment(Long commentId) {
        Optional<CommentEntity> commentEntity = commentRepository.findById(commentId);
        return  commentEntity.orElseThrow(()-> new ApiException(ErrorCodeComment.NULL_COMMENT));
    }

    @Transactional
    public void deleteComment(CommentEntity forDeleteComment) {
        commentRepository.delete(forDeleteComment);
    }
}
