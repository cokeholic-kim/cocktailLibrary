package org.cocktail.db.CocktailIngredient;

import org.cocktail.db.cocktail.CocktailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CocktailIngredientRepository extends JpaRepository<CocktailIngredientEntity,Long> {
    void deleteAllByCocktail(CocktailEntity cocktail);
}
