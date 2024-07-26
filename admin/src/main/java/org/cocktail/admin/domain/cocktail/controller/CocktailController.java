package org.cocktail.admin.domain.cocktail.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cocktail.admin.domain.Ingredient.business.IngredientBusiness;
import org.cocktail.admin.domain.Ingredient.controller.model.IngredientResponse;
import org.cocktail.admin.domain.cocktail.business.CocktailBusiness;
import org.cocktail.admin.domain.cocktail.controller.model.CockTailRequest;
import org.cocktail.admin.domain.cocktail.controller.model.CockTailUpdateRequest;
import org.cocktail.admin.domain.cocktail.controller.model.CocktailResponse;
import org.cocktail.db.CocktailIngredient.enums.Unit;
import org.cocktail.db.cocktail.CocktailEntity;
import org.cocktail.db.cocktail.enums.Glass;
import org.cocktail.db.cocktail.enums.Method;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cockTail")
@RequiredArgsConstructor
@Slf4j
public class CocktailController {

    private final CocktailBusiness cocktailBusiness;
    private final IngredientBusiness ingredientBusiness;
    private final ObjectMapper objectMapper;

    @GetMapping("/page")
    public String cocktailPage(Model model) {
        List<CocktailEntity> allCocktail = cocktailBusiness.getAllCocktail();
        List<IngredientResponse> ingredientResponses = ingredientBusiness.readAll();
        model.addAttribute("allCocktail", allCocktail);
        model.addAttribute("allIngredients", ingredientResponses);
        model.addAttribute("glasses", Glass.values());
        model.addAttribute("methods", Method.values());
        model.addAttribute("units", Unit.values());
        model.addAttribute("pagename","cocktail");

        return "cocktail/index";
    }

    @PostMapping("/register")
    public String register(
            @ModelAttribute CockTailRequest cockTailRequest
    ) throws IOException {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        cocktailBusiness.register(cockTailRequest, userName);
        return "redirect:/cocktail/index";
    }

    @GetMapping("/delete")
    @ResponseBody
    public ResponseEntity<Void> delete(@RequestParam String name, @RequestParam Long id) {
        cocktailBusiness.deleteCocktail(id, name);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/Detail/{id}")
    public String Detail(@PathVariable Long id, Model model) {
        CocktailResponse cocktail = cocktailBusiness.findCocktail(id);
        List<IngredientResponse> ingredientResponses = ingredientBusiness.readAll();

        model.addAttribute("cocktail", cocktail);
        model.addAttribute("allIngredients", ingredientResponses);
        model.addAttribute("units", Unit.values());

        return "cocktail/cockTailDetail";
    }

    @PostMapping("/update")
    @ResponseBody
    public String update(CockTailUpdateRequest request) throws IOException {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        cocktailBusiness.updateCocktail(request, userName);

        return "redirect:/cocktail/index";
    }

}
