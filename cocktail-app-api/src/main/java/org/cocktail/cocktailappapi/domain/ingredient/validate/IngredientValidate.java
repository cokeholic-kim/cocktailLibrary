package org.cocktail.cocktailappapi.domain.ingredient.validate;

import java.util.List;
import java.util.Optional;
import org.cocktail.common.exception.ApiException;
import org.cocktail.db.ingredient.IngredientEntity;
import org.springframework.stereotype.Component;

@Component
public class IngredientValidate {

    public void validateListIngredient(List<IngredientEntity> ingredientEntityList){
        if(ingredientEntityList.isEmpty()){
            throw new ApiException(ErrorCodeIngredient.EMPTY_INGREDIENT);
        }
    }
    public IngredientEntity validDetailIngredient(Optional<IngredientEntity> ingredient){
        return ingredient.orElseThrow(()->new ApiException(ErrorCodeIngredient.NULL_INGREDIENT));
    }
}
