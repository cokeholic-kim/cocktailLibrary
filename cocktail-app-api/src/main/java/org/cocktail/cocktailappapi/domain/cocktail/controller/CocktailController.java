package org.cocktail.cocktailappapi.domain.cocktail.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.cocktail.cocktailappapi.domain.cocktail.business.CocktailBusiness;
import org.cocktail.cocktailappapi.domain.cocktail.controller.model.CocktailResponse;
import org.cocktail.common.api.Api;
import org.cocktail.db.cocktail.enums.Glass;
import org.cocktail.db.cocktail.enums.Method;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cocktail")
@RequiredArgsConstructor
public class CocktailController {
    private final CocktailBusiness cocktailBusiness;

    @GetMapping("getAll")
    public Api<List<CocktailResponse>> getAllCocktail() {
        List<CocktailResponse> allCocktail = cocktailBusiness.getAllCocktail();
        return Api.OK(allCocktail);
    }

    @GetMapping("getDetail/{name}")
    public Api<CocktailResponse> getDetailCocktail(@PathVariable("name") String name) {
        CocktailResponse detailCocktail = cocktailBusiness.getDetailCocktail(name);
        return Api.OK(detailCocktail);
    }

    @GetMapping("glass")
    public Api<List<String>> getGlass() {
        return Api.OK(Arrays.stream(Glass.values()).map(Glass::getName).collect(Collectors.toList()));
    }

    @GetMapping("method")
    public Api<Method[]> getMethod() {
        return Api.OK(Method.values());
    }
}
