package org.cocktail.cocktailappapi.domain.ingredient.business;

import static org.cocktail.cocktailappapi.common.ErrorCodeFileSave.S3_SAVE_ERROR;

import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cocktail.cocktailappapi.common.S3UploadService;
import org.cocktail.cocktailappapi.domain.cocktail.controller.model.CocktailFit;
import org.cocktail.cocktailappapi.domain.cocktail.controller.model.IngredientListRequest;
import org.cocktail.cocktailappapi.domain.ingredient.controller.model.IngredientRequest;
import org.cocktail.cocktailappapi.domain.ingredient.controller.model.IngredientResponse;
import org.cocktail.cocktailappapi.domain.ingredient.converter.IngredientConverter;
import org.cocktail.cocktailappapi.domain.ingredient.service.IngredientService;
import org.cocktail.common.exception.ApiException;
import org.cocktail.db.cocktail.CocktailEntity;
import org.cocktail.db.file.FileEntity;
import org.cocktail.db.ingredient.IngredientEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IngredientBusiness {

    private final IngredientConverter ingredientConverter;
    private final IngredientService ingredientService;
    private  final S3UploadService uploadService;


    public List<IngredientResponse> getAllCocktail() {
        return ingredientConverter.toListResponse(ingredientService.findAllIngredients());
    }

    public IngredientResponse getDetailIngredient(String name) {
        return ingredientConverter.toResponse(ingredientService.getDetailIngredient(name));
    }

    public List<CocktailFit> getFitCocktails(IngredientListRequest ingredientListRequest) {
        List<CocktailEntity> fitCocktailList = ingredientService.findFitCocktailList(ingredientListRequest);
        return ingredientConverter.toCocktailFit(fitCocktailList,ingredientListRequest);
    }

    public IngredientResponse saveIngredient(IngredientRequest ingredientRequest, String username) {
        FileEntity fileEntity = new FileEntity();
        try {
            fileEntity = uploadService.saveFile(ingredientRequest.getImage(), username);
        } catch (IOException e) {
            throw new ApiException(S3_SAVE_ERROR);
        }
        IngredientEntity save = ingredientService.save(ingredientConverter.toEntity(ingredientRequest, fileEntity));
        return ingredientConverter.toResponse(save);
    }
}
