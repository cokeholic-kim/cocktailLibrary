package org.cocktail.admin.domain.Ingredient.converter;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.cocktail.admin.domain.Ingredient.controller.model.IngredientRequest;
import org.cocktail.admin.domain.Ingredient.controller.model.IngredientResponse;
import org.cocktail.admin.domain.Ingredient.controller.model.IngredientUpdateRequest;
import org.cocktail.common.Converter;
import org.cocktail.db.file.FileEntity;
import org.cocktail.db.file.FileRepository;
import org.cocktail.db.ingredient.IngredientEntity;
import org.cocktail.db.ingredient.enums.IngredientCategory;
import org.cocktail.db.ingredient.enums.IngredientStatus;

@Converter
@RequiredArgsConstructor
public class IngredientConverter {
    private final FileRepository fileRepository;

    public IngredientEntity toEntity(IngredientRequest request, FileEntity file) {
        return IngredientEntity.builder()
                .name(request.getIngredientName())
                .enName(request.getEnName())
                .category(IngredientCategory.fromCategoryName(request.getCategory()))
                .description(request.getDescription())
                .file(file)
                .status(IngredientStatus.REGISTERED)
                .build();
    }

    public IngredientResponse toResponse(IngredientEntity entity) {
        return IngredientResponse.builder()
                .name(entity.getName())
                .enName(entity.getEnName())
                .category(String.valueOf(entity.getCategory()))
                .description(entity.getDescription())
                .image(entity.getFile().getFilePath())
                .fileName(entity.getFile().getFileName())
                .build();
    }

    public IngredientEntity toEntity(IngredientUpdateRequest request, FileEntity file) {
        if (Objects.isNull(file)) {
            file = fileRepository.findByFileName(request.getExistingImage())
                    .orElseThrow(IllegalArgumentException::new);
        }

        return IngredientEntity.builder()
                .id(request.getId())
                .name(request.getName())
                .enName(request.getEnName())
                .category(IngredientCategory.fromCategoryName(request.getCategory()))
                .description(request.getDescription())
                .file(file)
                .status(IngredientStatus.REGISTERED)
                .build();
    }
}
