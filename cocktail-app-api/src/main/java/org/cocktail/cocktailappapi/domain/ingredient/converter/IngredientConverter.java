package org.cocktail.cocktailappapi.domain.ingredient.converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.cocktail.cocktailappapi.domain.cocktail.controller.model.CocktailFit;
import org.cocktail.cocktailappapi.domain.cocktail.controller.model.IngredientListRequest;
import org.cocktail.cocktailappapi.domain.cocktail.controller.model.SimpleCocktailResponse;
import org.cocktail.cocktailappapi.domain.ingredient.controller.model.IngredientRequest;
import org.cocktail.cocktailappapi.domain.ingredient.controller.model.IngredientResponse;
import org.cocktail.common.Converter;
import org.cocktail.db.CocktailIngredient.CocktailIngredientEntity;
import org.cocktail.db.cocktail.CocktailEntity;
import org.cocktail.db.file.FileEntity;
import org.cocktail.db.ingredient.IngredientEntity;
import org.cocktail.db.ingredient.enums.IngredientCategory;
import org.cocktail.db.ingredient.enums.IngredientStatus;

@Converter
@RequiredArgsConstructor
public class IngredientConverter {

    public List<IngredientResponse> toListResponse(List<IngredientEntity> ingredientEntities) {
        return ingredientEntities.stream().map(ingredient -> {
            return IngredientResponse.builder()
                    .ingredientName(ingredient.getName())
                    .enName(ingredient.getEnName())
                    .imagePath(ingredient.getFile().getFilePath())
                    .category(ingredient.getCategory())
                    .description(ingredient.getDescription())
                    .build();
        }).collect(Collectors.toList());
    }

    public IngredientResponse toResponse(IngredientEntity detailIngredient) {
        return IngredientResponse.builder()
                .ingredientName(detailIngredient.getName())
                .enName(detailIngredient.getEnName())
                .imagePath(detailIngredient.getFile().getFilePath())
                .category(detailIngredient.getCategory())
                .description(detailIngredient.getDescription())
                .usedCocktail(
                        getUsedCocktail(detailIngredient)
                )
                .build();
    }

    private static List<SimpleCocktailResponse> getUsedCocktail(IngredientEntity detailIngredient) {
        List<CocktailIngredientEntity> cocktailIngredientEntities = detailIngredient.getCocktailIngredients();
        if (cocktailIngredientEntities == null) {
            return Collections.emptyList();
        }
        return detailIngredient.getCocktailIngredients().stream().map(cocktailIngredientEntity -> {
            CocktailEntity cocktail = cocktailIngredientEntity.getCocktail();
            return SimpleCocktailResponse.builder()
                    .cocktailName(cocktail.getCocktailName())
                    .imagePath(cocktail.getFile().getFilePath())
                    .build();
        }).collect(Collectors.toList());
    }

    public List<CocktailFit> toCocktailFit(List<CocktailEntity> fitCocktailList,
                                           IngredientListRequest ingredientListRequest) {
        return fitCocktailList.stream().map(cocktail -> {
            return CocktailFit.builder()
                    .cocktailName(cocktail.getCocktailName())
                    .includeIngredients(getIncludeIngredients(cocktail, ingredientListRequest))
                    .excludeIngredient(getExcludeIngredients(cocktail, ingredientListRequest))
                    .imagePath(cocktail.getFile().getFilePath())
                    .build();
        }).collect(Collectors.toList());
    }

    private List<String> getIncludeIngredients(CocktailEntity cocktail, IngredientListRequest ingredients) {
        List<String> includeIngredients = new ArrayList<>();
        cocktail.getCocktailIngredients().stream()
                .map(cocktailIngredient -> cocktailIngredient.getIngredient().getName())
                .filter(ingredients.getMyIngredients()::contains)
                .forEach(includeIngredients::add);
        return includeIngredients;
    }

    private List<String> getExcludeIngredients(CocktailEntity cocktail, IngredientListRequest ingredients) {
        List<String> excludeIngredients = new ArrayList<>();
        List<String> includeIngredients = getIncludeIngredients(cocktail, ingredients);

        cocktail.getCocktailIngredients().stream()
                .map(cocktailIngredient -> cocktailIngredient.getIngredient().getName())
                .filter(ingredient -> !includeIngredients.contains(ingredient))
                .forEach(excludeIngredients::add);

        return excludeIngredients;
    }

    public IngredientEntity toEntity(IngredientRequest ingredientRequest, FileEntity file) {
        return IngredientEntity.builder()
                .name(ingredientRequest.getIngredientName())
                .enName(ingredientRequest.getEnName())
                .category(IngredientCategory.fromCategoryName(ingredientRequest.getCategory()))
                .description(ingredientRequest.getDescription())
                .file(file)
                .status(IngredientStatus.TEMPORARILY_REGISTERED)
                .build();
    }
}
