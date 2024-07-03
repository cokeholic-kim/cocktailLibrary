package org.cocktail.admin.domain.cocktail.controller.model;

import lombok.Builder;
import lombok.Data;
import org.cocktail.db.CocktailIngredient.enums.Unit;
import org.cocktail.db.ingredient.IngredientEntity;
@Data
@Builder
public class CocktailIngredientResponse {
    private Long id;
    private IngredientEntity ingredient;
    private Integer volume;
    private Unit unit;
}
