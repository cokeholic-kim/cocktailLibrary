package org.cocktail.cocktailappapi.domain.cocktail.validate;

import java.util.List;
import java.util.Optional;
import org.cocktail.common.exception.ApiException;
import org.cocktail.db.cocktail.CocktailEntity;
import org.springframework.stereotype.Component;

@Component
public class CocktailValidate {

    public void validateListCocktail(List<CocktailEntity> cocktailEntityList) {
        if (cocktailEntityList.isEmpty()) {
            throw new ApiException(ErrorCodeCocktail.EMPTY_COCKTAIL);
        }
    }

    public CocktailEntity validDetailCocktail(Optional<CocktailEntity> cocktail) {
        return cocktail.orElseThrow(() -> new ApiException(ErrorCodeCocktail.NULL_COCKTAIL));
    }
}
