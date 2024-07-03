package org.cocktail.cocktailappapi.domain.cocktail.controller.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SimpleCocktailResponse {
    private String cocktailName;
    private String imagePath;
}
