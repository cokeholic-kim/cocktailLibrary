package org.cocktail.admin.domain.Ingredient.controller.model;

import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;
@Getter
@Data
public class IngredientUpdateRequest {
    private Long id;
    private String name;
    private String enName;
    private String category;
    private String description;
    private String existingImage;
    private MultipartFile image;

    public IngredientUpdateRequest(Long id, String name, String enName, String category, String description,
                                   String existingImage, MultipartFile image) {
        this.id = id;
        this.name = name;
        this.enName = enName;
        this.category = category;
        this.description = description;
        this.existingImage = existingImage;
        this.image = image;
    }
}
