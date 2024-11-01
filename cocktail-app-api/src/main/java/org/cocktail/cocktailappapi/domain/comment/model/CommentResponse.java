package org.cocktail.cocktailappapi.domain.comment.model;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentResponse {
    public Long commentId;
    public Long parentCommentId;
    public String userEmail;
    public String comment;
    public LocalDateTime createdAt;
    public String category;
    public String itemName;
    public List<CommentResponse> children;
}
