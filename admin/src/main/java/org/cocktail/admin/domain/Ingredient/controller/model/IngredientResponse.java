package org.cocktail.admin.domain.Ingredient.controller.model;

import java.util.Optional;
import lombok.Builder;
import lombok.Data;
import org.cocktail.db.ingredient.enums.IngredientCategory;

@Data
@Builder
public class IngredientResponse {
    private final String name;
    private final String enName;
    private final String category;
    private final String description;
    private final String image;

    public IngredientResponse(String name, String enName, String category, String description, String image) {
        this.name = name;
        this.enName = enName;
        this.category = category;
        this.description = description;
        this.image = image;
    }
}
