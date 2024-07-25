package org.cocktail.admin.domain.cocktail.controller.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CocktailIngredientRequest {
    private String name;
    private Double volume;
    private String unit;
}

