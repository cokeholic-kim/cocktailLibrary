package org.cocktail.cocktailappapi.domain.ingredient.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cocktail.cocktailappapi.domain.ingredient.business.IngredientBusiness;
import org.cocktail.cocktailappapi.domain.ingredient.controller.model.IngredientResponse;
import org.cocktail.common.api.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ingredient")
@RequiredArgsConstructor
public class IngredientController {
    private final IngredientBusiness ingredientBusiness;

    @GetMapping("getAll")
    public Api<List<IngredientResponse>> getAllIngredient() {
        List<IngredientResponse> allIngredients = ingredientBusiness.getAllCocktail();
        return Api.OK(allIngredients);
    }

    @GetMapping("getDetail/{ingredientName}")
    public Api<IngredientResponse> getDetailIngredient(@PathVariable("ingredientName") String name) {
        IngredientResponse detailIngredient = ingredientBusiness.getDetailIngredient(name);
        return Api.OK(detailIngredient);
    }
}
