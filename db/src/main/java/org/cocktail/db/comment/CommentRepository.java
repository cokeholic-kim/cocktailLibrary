package org.cocktail.db.comment;

import java.util.List;
import org.cocktail.db.comment.enums.CommentRefCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<CommentEntity,Long> {
//    @Query(
//            "SELECT parent FROM CommentEntity parent "
//                    + "LEFT JOIN parent.children child ON child.deleted = false "
//                    + "WHERE parent.deleted = false "
//                    + "AND parent.refCategory = :refCategory "
//                    + "AND parent.refCategoryItem = :refCategoryItem "
//                    + "AND parent.parentComment IS NULL "
//                    + "ORDER BY parent.createdAt,child.createdAt "
//    )
//    List<CommentEntity> findAllByRefCategoryAndRefCategoryItemAndParentCommentIsNullAndDeletedFalseAndChildrenDeletedFalseOrderByCreatedAt(
//            @Param("refCategory") CommentRefCategory refCategory,
//            @Param("refCategoryItem") String refCategoryItem
//    );
    List<CommentEntity> findAllByRefCategoryAndRefCategoryItemAndParentCommentIsNullAndDeletedIsFalseOrderByCreatedAt(
            CommentRefCategory refCategory, String refCategoryItem);
}
