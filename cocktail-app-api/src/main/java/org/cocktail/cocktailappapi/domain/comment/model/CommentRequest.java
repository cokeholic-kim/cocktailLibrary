package org.cocktail.cocktailappapi.domain.comment.model;

import lombok.Data;

@Data
public class CommentRequest {
    private String content;
    private Long parentCommentId;
    private String refCategory;
    private String refCategoryItem;
}
