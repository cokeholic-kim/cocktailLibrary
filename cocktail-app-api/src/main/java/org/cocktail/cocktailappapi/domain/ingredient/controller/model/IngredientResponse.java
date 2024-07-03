package org.cocktail.cocktailappapi.domain.ingredient.controller.model;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.cocktail.cocktailappapi.domain.cocktail.controller.model.SimpleCocktailResponse;
import org.cocktail.db.ingredient.enums.IngredientCategory;

@Data
@Builder
public class IngredientResponse {
    private String ingredientName;
    private String enName;
    private IngredientCategory category;
    private String description;
    private String imagePath;
    private List<SimpleCocktailResponse> usedCocktail;
}
