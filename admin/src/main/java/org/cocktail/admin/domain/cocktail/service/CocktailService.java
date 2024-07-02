package org.cocktail.admin.domain.cocktail.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cocktail.db.CocktailIngredient.CocktailIngredientRepository;
import org.cocktail.db.cocktail.CocktailEntity;
import org.cocktail.db.cocktail.CocktailRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CocktailService {
    private final CocktailRepository cocktailRepository;
    private final CocktailIngredientRepository cocktailIngredientRepository;

    @Transactional
    public void register(CocktailEntity cocktailEntity) {
        cocktailRepository.save(cocktailEntity);
    }

    public List<CocktailEntity> allCocktail() {
        return cocktailRepository.findAll();
    }

    @Transactional
    public CocktailEntity findCocktail(Long id) {
        return cocktailRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Transactional
    public void updateCocktail(CocktailEntity newCocktail) {
        CocktailEntity old = cocktailRepository.findById(newCocktail.getId())
                .orElseThrow(IllegalArgumentException::new);

        cocktailIngredientRepository.deleteAllByCocktail(old);
        BeanUtils.copyProperties(newCocktail, old, "id");
    }

    public void deleteCocktail(Long id) {
        CocktailEntity byId = cocktailRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        cocktailRepository.delete(byId);
    }
}
