package org.cocktail.admin.domain.Ingredient.controller.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class IngredientRequest {
    private final String ingredientName;
    private final String enName;
    private final String category;
    private final String description;
    private final MultipartFile image;

}
