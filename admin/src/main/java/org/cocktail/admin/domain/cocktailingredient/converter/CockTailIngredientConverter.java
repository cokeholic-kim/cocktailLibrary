package org.cocktail.admin.domain.cocktailingredient.converter;

import lombok.RequiredArgsConstructor;
import org.cocktail.admin.domain.cocktail.controller.model.CocktailIngredientRequest;
import org.cocktail.common.Converter;
import org.cocktail.db.CocktailIngredient.CocktailIngredientEntity;
import org.cocktail.db.CocktailIngredient.enums.Unit;
import org.cocktail.db.ingredient.IngredientEntity;
import org.cocktail.db.ingredient.IngredientRepository;

@Converter
@RequiredArgsConstructor
public class CockTailIngredientConverter {
    private final IngredientRepository ingredientRepository;

    public CocktailIngredientEntity toEntity(CocktailIngredientRequest cocktailIngredientRequest) {
        IngredientEntity byName = ingredientRepository.findByName(cocktailIngredientRequest.getName())
                .orElseThrow(IllegalArgumentException::new);

        return CocktailIngredientEntity.builder()
                .ingredient(byName)
                .volume(Double.valueOf(cocktailIngredientRequest.getVolume()))
                .unit(Unit.valueOf(cocktailIngredientRequest.getUnit()))
                .build();
    }
}
