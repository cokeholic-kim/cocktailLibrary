package org.cocktail.db.cocktail;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.cocktail.db.BaseEntity;
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@Table(name = "cocktail")
public class CocktailEntity extends BaseEntity {
    @Column(nullable = false)
    private Long user_id;

    @Column(length = 50, nullable = false)
    private String cocktailName;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String ingredients;

    @Column(nullable = false)
    private double proof;

    @Column(length = 50, nullable = false)
    private String glass;

    @Column(length = 50, nullable = false)
    private String method;

    private String garnish;
    private String image;
}
