package org.cocktail.cocktailappapi.domain.ingredient.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cocktail.cocktailappapi.domain.ingredient.validate.IngredientValidate;
import org.cocktail.db.ingredient.IngredientEntity;
import org.cocktail.db.ingredient.IngredientRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IngredientService {
    private final IngredientRepository ingredientRepository;
    private final IngredientValidate validate;

    public List<IngredientEntity> findAllIngredients(){
        List<IngredientEntity> ingredientEntityList = ingredientRepository.findAll();
        validate.validateListIngredient(ingredientEntityList);
        return ingredientEntityList;
    }

    public IngredientEntity getDetailIngredient(String name) {
       return validate.validDetailIngredient(ingredientRepository.findByName(name));
    }
}
