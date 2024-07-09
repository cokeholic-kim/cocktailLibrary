package org.cocktail.cocktailappapi.domain.cocktail.controller.model;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IngredientListRequest {
    private List<String> myIngredients;
}
