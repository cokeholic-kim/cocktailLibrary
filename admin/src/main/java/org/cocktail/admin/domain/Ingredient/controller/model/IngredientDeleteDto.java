package org.cocktail.admin.domain.Ingredient.controller.model;

import lombok.Data;

@Data
public class IngredientDeleteDto {
    private final String name;
    private final String fileName;
}
