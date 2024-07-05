package org.cocktail.cocktailappapi.domain.cocktail.business;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cocktail.cocktailappapi.domain.cocktail.controller.model.CocktailResponse;
import org.cocktail.cocktailappapi.domain.cocktail.converter.CocktailConverter;
import org.cocktail.cocktailappapi.domain.cocktail.service.CocktailService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CocktailBusiness {

    private final CocktailConverter cocktailConverter;
    private final CocktailService cocktailService;


    public List<CocktailResponse> getAllCocktail() {
        return cocktailConverter.toListResponse(cocktailService.findAllCocktail());
    }

    public CocktailResponse getDetailCocktail(String name) {
        return cocktailConverter.toResponse(cocktailService.findCocktail(name));
    }
}
