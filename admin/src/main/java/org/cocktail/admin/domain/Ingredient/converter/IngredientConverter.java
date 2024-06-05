package org.cocktail.admin.domain.Ingredient.converter;

import static org.cocktail.admin.common.UploadService.createFileName;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.cocktail.admin.common.Converter;
import org.cocktail.admin.domain.Ingredient.controller.model.IngredientRequest;
import org.cocktail.admin.domain.Ingredient.controller.model.IngredientResponse;
import org.cocktail.admin.domain.Ingredient.controller.model.IngredientUpdateRequest;
import org.cocktail.db.ingredient.IngredientEntity;
import org.cocktail.db.ingredient.enums.IngredientCategory;

@Converter
@RequiredArgsConstructor
public class IngredientConverter {
    public IngredientEntity toEntity(IngredientRequest request){
        return IngredientEntity.builder()
                .name(request.getIngredientName())
                .enName(request.getEnName())
                .category(IngredientCategory.fromCategoryName(request.getCategory()))
                .description(request.getDescription())
                .image(createFileName(request.getImage()))
                .build();
    }

    public IngredientResponse toResponse(IngredientEntity entity){
        return IngredientResponse.builder()
                .name(entity.getName())
                .enName(entity.getEnName())
                .category(String.valueOf(entity.getCategory()))
                .description(entity.getDescription())
                .image(entity.getImage())
                .build();
    }

    public IngredientEntity toEntity(IngredientUpdateRequest request) {
        String imageName = request.getImage().isEmpty() ? request.getExistingImage() : createFileName(request.getImage()) ;
        return IngredientEntity.builder()
                .id(request.getId())
                .name(request.getName())
                .enName(request.getEnName())
                .category(IngredientCategory.fromCategoryName(request.getCategory()))
                .description(request.getDescription())
                .image(imageName)
                .build();
    }
}
