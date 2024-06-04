package org.cocktail.admin.domain.Ingredient.controller;

import lombok.RequiredArgsConstructor;
import org.cocktail.admin.domain.Ingredient.business.IngredientBusiness;
import org.cocktail.admin.domain.Ingredient.controller.model.IngredientRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ingredient")
public class IngredientController {

    private final IngredientBusiness ingredientBusiness;

    @PostMapping("/register")
    public String register(IngredientRequest ingredientRequest) {
        ingredientBusiness.register(ingredientRequest);
        return "redirect:/";
    }

}
