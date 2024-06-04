package org.cocktail.admin.domain.Ingredient.controller.model;

import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Data
@Getter
public class IngredientRequest {
    private final String ingredientName;
    private final String enName;
    private final String category;
    private final String description;
    private final MultipartFile image;

    public IngredientRequest(String ingredientName, String enName, String category, String description,
                             MultipartFile image) {
        this.ingredientName = ingredientName;
        this.enName = enName;
        this.category = category;
        this.description = description;
        this.image = image;
    }
}
