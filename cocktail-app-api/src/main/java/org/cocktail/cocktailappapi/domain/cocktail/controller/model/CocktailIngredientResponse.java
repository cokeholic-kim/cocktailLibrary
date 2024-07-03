package org.cocktail.cocktailappapi.domain.cocktail.controller.model;

import lombok.Builder;
import lombok.Data;
import org.cocktail.db.CocktailIngredient.enums.Unit;
@Data
@Builder
public class CocktailIngredientResponse {
    private String ingredientName;
    private Double volume;
    private Unit unit;
    private String imagePath;
}
