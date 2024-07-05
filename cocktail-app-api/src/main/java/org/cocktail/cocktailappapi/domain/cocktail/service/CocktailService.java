package org.cocktail.cocktailappapi.domain.cocktail.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.cocktail.cocktailappapi.domain.cocktail.controller.model.CocktailResponse;
import org.cocktail.cocktailappapi.domain.cocktail.validate.CocktailValidate;
import org.cocktail.db.cocktail.CocktailEntity;
import org.cocktail.db.cocktail.CocktailRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CocktailService {
    private final CocktailRepository cocktailRepository;
    private final CocktailValidate validate;

    public List<CocktailEntity> findAllCocktail(){

        List<CocktailEntity> all = cocktailRepository.findAll();
        validate.validateListCocktail(all);
        return all;
    }

    public CocktailEntity findCocktail(String name) {
        Optional<CocktailEntity> byCocktailName = cocktailRepository.findByCocktailName(name);
        return validate.validDetailCocktail(byCocktailName);
    }
}
