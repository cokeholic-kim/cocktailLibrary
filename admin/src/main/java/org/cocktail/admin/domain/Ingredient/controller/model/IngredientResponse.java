package org.cocktail.admin.domain.Ingredient.controller.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IngredientResponse {
    private final String name;
    private final String enName;
    private final String category;
    private final String description;
    private final String image;
    private final String fileName;
}
