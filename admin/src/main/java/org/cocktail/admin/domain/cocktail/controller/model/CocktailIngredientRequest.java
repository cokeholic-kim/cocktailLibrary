package org.cocktail.admin.domain.cocktail.controller.model;

import lombok.Data;

@Data
public class  CocktailIngredientRequest {
    private String name;
    private Integer volume;
    private String unit;
}

