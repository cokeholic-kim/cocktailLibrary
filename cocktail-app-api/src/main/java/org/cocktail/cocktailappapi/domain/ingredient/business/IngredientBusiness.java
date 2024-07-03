package org.cocktail.cocktailappapi.domain.ingredient.business;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cocktail.cocktailappapi.domain.ingredient.controller.model.IngredientResponse;
import org.cocktail.cocktailappapi.domain.ingredient.converter.IngredientConverter;
import org.cocktail.cocktailappapi.domain.ingredient.service.IngredientService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IngredientBusiness {

    private final IngredientConverter ingredientConverter;
    private final IngredientService ingredientService;


    public List<IngredientResponse> getAllCocktail() {
        return ingredientConverter.toListResponse(ingredientService.findAllIngredients());
    }

    public IngredientResponse getDetailIngredient(String name) {
        return ingredientConverter.toResponse(ingredientService.getDetailIngredient(name));
    }
}
