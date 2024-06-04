package org.cocktail.admin.domain.Ingredient.controller;

import lombok.RequiredArgsConstructor;
import org.cocktail.admin.domain.Ingredient.business.IngredientBusiness;
import org.cocktail.admin.domain.Ingredient.controller.model.IngredientRequest;
import org.cocktail.admin.domain.Ingredient.controller.model.IngredientUpdateRequest;
import org.cocktail.db.ingredient.IngredientEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/delete/{name}")
    public String delete(@PathVariable String name){
        ingredientBusiness.delete(name);
        return "redirect:/";
    }

    @GetMapping("/detail/{name}")
    public String detail(@PathVariable String name, Model model){
        IngredientEntity ingredient = ingredientBusiness.detail(name);
        model.addAttribute("ingredient",ingredient);
        return "cocktail/ingredientDetail";
    }

    @PostMapping("/update")
    public String update(IngredientUpdateRequest request){
        System.out.println("request = " + request);
        System.out.println(request.getImage().isEmpty());
        ingredientBusiness.update(request);
        return "redirect:/";
    }

}
