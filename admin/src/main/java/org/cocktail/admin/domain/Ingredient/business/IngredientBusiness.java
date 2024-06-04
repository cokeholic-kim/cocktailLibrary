package org.cocktail.admin.domain.Ingredient.business;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cocktail.admin.domain.Ingredient.controller.model.IngredientRequest;
import org.cocktail.admin.domain.Ingredient.controller.model.IngredientResponse;
import org.cocktail.admin.domain.Ingredient.converter.IngredientConverter;
import org.cocktail.admin.domain.Ingredient.service.IngredientService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IngredientBusiness {

    private final IngredientService ingredientService;
    private final IngredientConverter converter;

    public void register(IngredientRequest request) {
        ingredientService.register(converter.toEntity(request),request.getImage());
    }

    public List<IngredientResponse> readAll() {
        return ingredientService.findALl().stream().map(converter::toResponse).toList();
    }
}
