package org.cocktail.cocktailappapi.domain.comment.converter;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.cocktail.cocktailappapi.domain.comment.model.CommentRequest;
import org.cocktail.cocktailappapi.domain.comment.model.CommentResponse;
import org.cocktail.common.Converter;
import org.cocktail.db.comment.CommentEntity;
import org.cocktail.db.comment.enums.CommentRefCategory;

@Converter
@RequiredArgsConstructor
public class CommentConverter {

    public CommentEntity toEntity(CommentRequest comment) {
        return CommentEntity.builder()
                .content(comment.getContent())
                .refCategory(CommentRefCategory.valueOf(comment.getRefCategory()))
                .refCategoryItem(comment.getRefCategoryItem())
                .createdAt(LocalDateTime.now())
                .deleted(false)
                .build();
    }

    public CommentResponse toResponse(CommentEntity commentEntity) {
        CommentResponse commentResponse = CommentResponse.builder()
                .commentId(commentEntity.getId())
                .userEmail(commentEntity.getUser().getEmail())
                .createdAt(commentEntity.getCreatedAt())
                .comment(commentEntity.getContent())
                .category(commentEntity.getRefCategory().toString())
                .itemName(commentEntity.getRefCategoryItem())
                .itemName(commentEntity.getRefCategoryItem())
                // TODO 데이터를 조회할때 delete값들을 모두 필터링할수있도록 수정필요 추후 delete된값들이 많아지면 비효율 적임
                .children(commentEntity.getChildren().stream().filter((comment)->!comment.isDeleted()).map(this::toResponse).toList())
                .build();

        if(commentEntity.getParentComment() != null){
            commentResponse.setParentCommentId(commentEntity.getParentComment().getId());
        }

        return commentResponse;
    }
}
