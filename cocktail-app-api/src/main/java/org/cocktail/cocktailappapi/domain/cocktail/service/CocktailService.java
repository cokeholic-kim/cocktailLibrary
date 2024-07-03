package org.cocktail.cocktailappapi.domain.cocktail.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cocktail.db.cocktail.CocktailEntity;
import org.cocktail.db.cocktail.CocktailRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CocktailService {
    private final CocktailRepository cocktailRepository;

    public List<CocktailEntity> findAllCocktail(){
        return cocktailRepository.findAll();
    }
}
