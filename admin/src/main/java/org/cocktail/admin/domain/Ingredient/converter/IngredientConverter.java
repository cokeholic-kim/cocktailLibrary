package org.cocktail.admin.domain.Ingredient.converter;

import lombok.RequiredArgsConstructor;
import org.cocktail.admin.common.Converter;
import org.cocktail.admin.domain.Ingredient.controller.model.IngredientRequest;
import org.cocktail.admin.domain.Ingredient.controller.model.IngredientResponse;
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
                .image(request.getImagePath())
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
}
