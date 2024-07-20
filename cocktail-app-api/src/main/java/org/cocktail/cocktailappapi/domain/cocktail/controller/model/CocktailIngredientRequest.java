package org.cocktail.cocktailappapi.domain.cocktail.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CocktailIngredientRequest {
    private String name;
    private Double volume;
    private String unit;


}

