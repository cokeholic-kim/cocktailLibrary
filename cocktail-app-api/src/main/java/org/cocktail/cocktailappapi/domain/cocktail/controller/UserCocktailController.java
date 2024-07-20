package org.cocktail.cocktailappapi.domain.cocktail.controller;

import lombok.RequiredArgsConstructor;
import org.cocktail.cocktailappapi.domain.cocktail.business.CocktailBusiness;
import org.cocktail.cocktailappapi.domain.cocktail.controller.model.CocktailRequest;
import org.cocktail.cocktailappapi.domain.cocktail.controller.model.CocktailResponse;
import org.cocktail.common.api.Api;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserCocktailController {
    private final CocktailBusiness cocktailBusiness;

    @PostMapping("saveCocktail")
    public Api<CocktailResponse> saveCocktail(
            CocktailRequest cocktailRequest
    ) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        CocktailResponse cocktailResponse = cocktailBusiness.saveCocktail(cocktailRequest, username);
        return Api.OK(cocktailResponse);
    }
}
