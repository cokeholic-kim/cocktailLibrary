package org.cocktail.cocktailappapi.domain.cocktail.converter;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.cocktail.cocktailappapi.domain.cocktail.controller.model.CocktailIngredientResponse;
import org.cocktail.cocktailappapi.domain.cocktail.controller.model.CocktailRequest;
import org.cocktail.cocktailappapi.domain.cocktail.controller.model.CocktailResponse;
import org.cocktail.common.Converter;
import org.cocktail.db.CocktailIngredient.CocktailIngredientEntity;
import org.cocktail.db.cocktail.CocktailEntity;
import org.cocktail.db.cocktail.enums.CocktailStatus;
import org.cocktail.db.cocktail.enums.Glass;
import org.cocktail.db.cocktail.enums.Method;
import org.cocktail.db.file.FileEntity;

@Converter
@RequiredArgsConstructor
public class CocktailConverter {

    public List<CocktailResponse> toListResponse(List<CocktailEntity> allCocktail) {
        return allCocktail.stream().map(this::toResponse).collect(Collectors.toList());
    }

    private static CocktailIngredientResponse toCocktailIngredientResponse(CocktailIngredientEntity ingredient) {
        return CocktailIngredientResponse.builder()
                .ingredientName(ingredient.getIngredient().getName())
                .volume(ingredient.getVolume())
                .unit(ingredient.getUnit())
                .imagePath(ingredient.getIngredient().getFile().getFilePath())
                .build();
    }

    public CocktailResponse toResponse(CocktailEntity cocktail) {
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
                .status(String.valueOf(cocktail.getStatus()))
                .build();
    }


    public CocktailEntity toEntity(CocktailRequest request, FileEntity fileEntity,
                                   List<CocktailIngredientEntity> cocktailIngredientEntities) {
        CocktailEntity cocktailEntity = CocktailEntity.builder()
                .user(fileEntity.getUploader())
                .cocktailName(request.getCocktailName())
                .glass(Glass.fromGlassName(request.getGlass()))
                .garnish(request.getGarnish())
                .method(Method.valueOf(request.getMethod()))
                .proof(request.getProof())
                .description(request.getDescription())
                .file(fileEntity)
                .status(CocktailStatus.USER_REGISTERED)
                .build();

        for (CocktailIngredientEntity ingredient : cocktailIngredientEntities) {
            ingredient.setCocktail(cocktailEntity);
        }

        cocktailEntity.setCocktailIngredients(cocktailIngredientEntities);
        return cocktailEntity;
    }
}
