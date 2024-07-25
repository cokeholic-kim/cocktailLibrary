package org.cocktail.cocktailappapi.domain.ingredient.validate;

import static org.cocktail.cocktailappapi.domain.ingredient.validate.ErrorCodeIngredient.DUPLICATE_INGREDIENT;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.cocktail.common.exception.ApiException;
import org.cocktail.db.ingredient.IngredientEntity;
import org.cocktail.db.ingredient.IngredientRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IngredientValidate {
    private final IngredientRepository ingredientRepository;

    public void validateListIngredient(List<IngredientEntity> ingredientEntityList){
        if(ingredientEntityList.isEmpty()){
            throw new ApiException(ErrorCodeIngredient.EMPTY_INGREDIENT);
        }
    }
    public IngredientEntity validDetailIngredient(Optional<IngredientEntity> ingredient){
        return ingredient.orElseThrow(()->new ApiException(ErrorCodeIngredient.NULL_INGREDIENT));
    }

    public void validateDuplicateName(IngredientEntity entity){
        if(ingredientRepository.existsByName(entity.getName()) || ingredientRepository.existsByEnName(entity.getEnName())){
            throw new ApiException(DUPLICATE_INGREDIENT);
        }
    }
}
