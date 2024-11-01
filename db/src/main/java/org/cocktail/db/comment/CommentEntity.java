package org.cocktail.db.comment;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.cocktail.db.BaseEntity;
import org.cocktail.db.comment.enums.CommentRefCategory;
import org.cocktail.db.user.UserEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@Table(name = "comment")
public class CommentEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "parent_comment")
    private CommentEntity parentComment;

    @Column(nullable = false, length = 500, columnDefinition = "VARCHAR(500) COLLATE utf8mb4_general_ci")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(50)", length = 50,nullable = false)
    private CommentRefCategory refCategory;

    @Column(name = "ref_category_item", nullable = false)
    private String refCategoryItem;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false,columnDefinition = "TINYINT(1)")
    private boolean deleted;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentEntity> children;
}
