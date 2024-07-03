package org.cocktail.cocktailappapi.domain.cocktail.converter;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.cocktail.cocktailappapi.domain.cocktail.controller.model.CocktailIngredientResponse;
import org.cocktail.cocktailappapi.domain.cocktail.controller.model.CocktailResponse;
import org.cocktail.common.Converter;
import org.cocktail.db.CocktailIngredient.CocktailIngredientEntity;
import org.cocktail.db.cocktail.CocktailEntity;

@Converter
@RequiredArgsConstructor
public class CocktailConverter {

    public List<CocktailResponse> toResponse(List<CocktailEntity> allCocktail) {
        return allCocktail.stream().map(cocktail -> {
            List<CocktailIngredientResponse> cocktailIngredientResponseList = cocktail.getCocktailIngredients().stream()
                    .map(CocktailConverter::toCocktailIngredientResponse).collect(Collectors.toList());

            return CocktailResponse.builder()
                    .cocktailName(cocktail.getCocktailName())
                    .proof(cocktail.getProof())
                    .glass(cocktail.getGlass())
                    .method(cocktail.getMethod())
                    .garnish(cocktail.getGarnish())
                    .description(cocktail.getDescription())
                    .imagePath(cocktail.getFile().getFilePath())
                    .ingredients(cocktailIngredientResponseList)
                    .build();
        }).collect(Collectors.toList());
    }

    private static CocktailIngredientResponse toCocktailIngredientResponse(CocktailIngredientEntity ingredient) {
        return CocktailIngredientResponse.builder()
                .ingredientName(ingredient.getIngredient().getName())
                .volume(ingredient.getVolume())
                .unit(ingredient.getUnit())
                .imagePath(ingredient.getIngredient().getFile().getFilePath())
                .build();
    }
}
