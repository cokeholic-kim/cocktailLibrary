package org.cocktail.cocktailappapi.domain.ingredient.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cocktail.cocktailappapi.domain.ingredient.business.IngredientBusiness;
import org.cocktail.cocktailappapi.domain.ingredient.controller.model.IngredientRequest;
import org.cocktail.cocktailappapi.domain.ingredient.controller.model.IngredientResponse;
import org.cocktail.common.api.Api;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("user")
@RestController
@RequiredArgsConstructor
@Slf4j
public class UserIngredientController {
    private final IngredientBusiness ingredientBusiness;
    @PostMapping("ingredientRegister")
    public Api<IngredientResponse> ingredientRegister(IngredientRequest ingredientRequest) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        IngredientResponse ingredientResponse = ingredientBusiness.saveIngredient(ingredientRequest, username);
        return Api.OK(ingredientResponse);
    }
}
