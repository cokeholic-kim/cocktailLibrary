package org.cocktail.db.CocktailIngredient;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.cocktail.db.BaseEntity;
import org.cocktail.db.CocktailIngredient.enums.Unit;
import org.cocktail.db.cocktail.CocktailEntity;
import org.cocktail.db.ingredient.IngredientEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@Table(name = "cocktail_ingredient")
public class CocktailIngredientEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "cocktail_id")
    private CocktailEntity cocktail;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private IngredientEntity ingredient;

    private Integer volume;
    @Column(columnDefinition = "varchar(255)", length = 100, nullable = false)
    @Enumerated(EnumType.STRING)
    private Unit unit;
}
