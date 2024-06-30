package org.cocktail.admin.domain.Ingredient.business;

import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cocktail.admin.common.S3UploadService;
import org.cocktail.admin.domain.Ingredient.controller.model.IngredientRequest;
import org.cocktail.admin.domain.Ingredient.controller.model.IngredientResponse;
import org.cocktail.admin.domain.Ingredient.controller.model.IngredientUpdateRequest;
import org.cocktail.admin.domain.Ingredient.converter.IngredientConverter;
import org.cocktail.admin.domain.Ingredient.service.IngredientService;
import org.cocktail.db.file.FileEntity;
import org.cocktail.db.ingredient.IngredientEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IngredientBusiness {

    private final IngredientService ingredientService;
    private final S3UploadService s3UploadService;
    private final IngredientConverter converter;

    public void register(IngredientRequest request, String userName) throws IOException {

        FileEntity fileEntity = s3UploadService.saveFile(request.getImage(), userName);
        ingredientService.register(converter.toEntity(request, fileEntity));
    }

    public void update(IngredientUpdateRequest request, String userName) throws IOException {
        FileEntity file = null;
        if (!request.getImage().isEmpty()) {
            file = s3UploadService.updateFile(request.getExistingImage(), request.getImage(), userName);
        }
        IngredientEntity entity = converter.toEntity(request, file);

        ingredientService.update(entity);
    }

    public List<IngredientResponse> readAll() {
        return ingredientService.findALl().stream().map(converter::toResponse).toList();
    }

    public void delete(String name, String filename) {
        s3UploadService.deleteFile(filename);
        ingredientService.delete(name);
    }

    public IngredientEntity detail(String name) {
        return ingredientService.findByName(name);
    }

    public List<IngredientResponse> search(String name) {
        List<IngredientEntity> search = ingredientService.search(name);
        return search.stream().map(converter::toResponse)
                .toList();
    }
}
