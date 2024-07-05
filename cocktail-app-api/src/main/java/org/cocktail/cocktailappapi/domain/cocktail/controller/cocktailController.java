package org.cocktail.cocktailappapi.domain.cocktail.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cocktail.cocktailappapi.domain.cocktail.business.CocktailBusiness;
import org.cocktail.cocktailappapi.domain.cocktail.controller.model.CocktailResponse;
import org.cocktail.common.api.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cocktail")
@RequiredArgsConstructor
public class cocktailController {
    private final CocktailBusiness cocktailBusiness;

    @GetMapping("getAll")
    public Api<List<CocktailResponse>> getAllCocktail(){
        List<CocktailResponse> allCocktail = cocktailBusiness.getAllCocktail();
        return Api.OK(allCocktail);
    }

    @GetMapping("getDetail/{name}")
    public Api<CocktailResponse> getDetailCocktail(@PathVariable("name") String name){
        CocktailResponse detailCocktail = cocktailBusiness.getDetailCocktail(name);
        return Api.OK(detailCocktail);
    }
}
