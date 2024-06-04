package org.cocktail.admin.domain.security.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cocktail.admin.common.UploadService;
import org.cocktail.admin.domain.Ingredient.business.IngredientBusiness;
import org.cocktail.admin.domain.Ingredient.controller.model.IngredientResponse;
import org.cocktail.admin.domain.cocktail.business.CocktailBusiness;
import org.cocktail.admin.domain.security.business.UserBusiness;
import org.cocktail.db.cocktail.CocktailEntity;
import org.cocktail.db.cocktail.enums.Glass;
import org.cocktail.db.cocktail.enums.Method;
import org.cocktail.db.ingredient.enums.IngredientCategory;
import org.cocktail.db.user.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final CocktailBusiness cocktailBusiness;
    private final UserBusiness userBusiness;
    private final IngredientBusiness ingredientBusiness;

    @GetMapping("/")
    public String mainPage(Model model) {
        List<CocktailEntity> allCocktail = cocktailBusiness.getAllCocktail();
        model.addAttribute("allCocktail", allCocktail);

        List<UserEntity> userEntities = userBusiness.readAllUser();
        model.addAttribute("allUser",userEntities);

        List<IngredientResponse> ingredientResponses = ingredientBusiness.readAll();
        model.addAttribute("allIngredients",ingredientResponses);

        model.addAttribute("glasses", Glass.values());
        model.addAttribute("methods", Method.values());
        model.addAttribute("ingredientCategory", IngredientCategory.values());
        model.addAttribute("uploadPath", UploadService.UPLOAD_PATH);

        return "index";
    }
}
