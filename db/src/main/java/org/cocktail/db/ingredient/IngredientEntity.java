package org.cocktail.db.ingredient;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.cocktail.db.BaseEntity;
import org.cocktail.db.file.FileEntity;
import org.cocktail.db.ingredient.enums.IngredientCategory;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@Table(name = "ingredient")
public class IngredientEntity extends BaseEntity {
    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String enName;

    @Column(columnDefinition = "varchar(255)", length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private IngredientCategory category;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "file_id")
    private FileEntity file;
}
