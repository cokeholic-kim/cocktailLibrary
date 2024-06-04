package org.cocktail.admin.domain.Ingredient.business;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cocktail.admin.domain.Ingredient.controller.model.IngredientRequest;
import org.cocktail.admin.domain.Ingredient.controller.model.IngredientResponse;
import org.cocktail.admin.domain.Ingredient.controller.model.IngredientUpdateRequest;
import org.cocktail.admin.domain.Ingredient.converter.IngredientConverter;
import org.cocktail.admin.domain.Ingredient.service.IngredientService;
import org.cocktail.db.ingredient.IngredientEntity;
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

    public void delete(String name) {
        ingredientService.delete(name);

    }

    public IngredientEntity detail(String name) {
        return ingredientService.findByName(name);
    }

    public void update(IngredientUpdateRequest request) {
        ingredientService.update(converter.toEntity(request),request.getExistingImage(),request.getImage());
    }
}
