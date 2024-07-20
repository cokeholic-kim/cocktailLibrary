package org.cocktail.cocktailappapi.domain.cocktail.controller.model;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.cocktail.db.cocktail.enums.Glass;
import org.cocktail.db.cocktail.enums.Method;
@Data
@Builder
public class CocktailResponse {
    private String cocktailName;
    private double proof;
    private Glass glass;
    private Method method;
    private String garnish;
    private String description;
    private String imagePath;
    private String status;
    private List<CocktailIngredientResponse> ingredients;
}
