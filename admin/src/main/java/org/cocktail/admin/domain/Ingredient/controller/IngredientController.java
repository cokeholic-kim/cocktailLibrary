package org.cocktail.admin.domain.Ingredient.controller;

import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cocktail.admin.domain.Ingredient.business.IngredientBusiness;
import org.cocktail.admin.domain.Ingredient.controller.model.IngredientRequest;
import org.cocktail.admin.domain.Ingredient.controller.model.IngredientResponse;
import org.cocktail.admin.domain.Ingredient.controller.model.IngredientUpdateRequest;
import org.cocktail.db.ingredient.IngredientEntity;
import org.cocktail.db.ingredient.enums.IngredientCategory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ingredient")
public class IngredientController {

    private final IngredientBusiness ingredientBusiness;

    @GetMapping("/page")
    public String ingredientPage(Model model){
        List<IngredientResponse> ingredientResponses = ingredientBusiness.readAll();
        model.addAttribute("allIngredients", ingredientResponses);
        model.addAttribute("ingredientCategory", IngredientCategory.values());


        return "ingredient/index";
    }

    @PostMapping("/register")
    public String register(IngredientRequest ingredientRequest) throws IOException {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        ingredientBusiness.register(ingredientRequest,userName);
        return "redirect:/ingredient/page";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam String name, @RequestParam String filename){
        ingredientBusiness.delete(name,filename);
        return "redirect:/ingredient/page";
    }

    @GetMapping("/detail/{name}")
    public String detail(@PathVariable("name") String name, Model model){
        IngredientEntity ingredient = ingredientBusiness.detail(name);
        model.addAttribute("ingredient",ingredient);
        return "ingredient/ingredientDetail";
    }

    @PostMapping("/update")
    @ResponseBody
    public String update(IngredientUpdateRequest request) throws IOException {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        ingredientBusiness.update(request,userName);
        return "redirect:/ingredient/detail";
    }

}
