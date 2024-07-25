package org.cocktail.cocktailappapi.domain.ingredient.controller.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class IngredientRequest {
    private final String ingredientName;
    private final String enName;
    private final MultipartFile image;
    private final String description;
    private final String category;
}
