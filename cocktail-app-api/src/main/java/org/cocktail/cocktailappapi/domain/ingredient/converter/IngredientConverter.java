package org.cocktail.cocktailappapi.domain.ingredient.converter;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.cocktail.cocktailappapi.domain.cocktail.controller.model.SimpleCocktailResponse;
import org.cocktail.cocktailappapi.domain.ingredient.controller.model.IngredientResponse;
import org.cocktail.common.Converter;
import org.cocktail.db.cocktail.CocktailEntity;
import org.cocktail.db.ingredient.IngredientEntity;

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
        return detailIngredient.getCocktailIngredients().stream().map(cocktailIngredientEntity -> {
            CocktailEntity cocktail = cocktailIngredientEntity.getCocktail();
            return SimpleCocktailResponse.builder()
                    .cocktailName(cocktail.getCocktailName())
                    .imagePath(cocktail.getFile().getFilePath())
                    .build();
        }).collect(Collectors.toList());
    }

}
