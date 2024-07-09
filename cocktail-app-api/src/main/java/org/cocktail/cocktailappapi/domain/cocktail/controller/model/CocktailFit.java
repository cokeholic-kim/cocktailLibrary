package org.cocktail.cocktailappapi.domain.cocktail.controller.model;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CocktailFit {
    private final String cocktailName;
    private final List<String> includeIngredients;
    private final List<String> excludeIngredient;
    private final String imagePath;
}
