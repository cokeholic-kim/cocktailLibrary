package org.cocktail.cocktailappapi.domain.cocktail.business;

import static org.cocktail.cocktailappapi.common.ErrorCodeFileSave.S3_SAVE_ERROR;

import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cocktail.cocktailappapi.common.S3UploadService;
import org.cocktail.cocktailappapi.domain.cocktail.controller.model.CocktailRequest;
import org.cocktail.cocktailappapi.domain.cocktail.controller.model.CocktailResponse;
import org.cocktail.cocktailappapi.domain.cocktail.converter.CockTailIngredientConverter;
import org.cocktail.cocktailappapi.domain.cocktail.converter.CocktailConverter;
import org.cocktail.cocktailappapi.domain.cocktail.service.CocktailService;
import org.cocktail.common.exception.ApiException;
import org.cocktail.db.CocktailIngredient.CocktailIngredientEntity;
import org.cocktail.db.cocktail.CocktailEntity;
import org.cocktail.db.file.FileEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CocktailBusiness {

    private final CocktailConverter cocktailConverter;
    private final CocktailService cocktailService;
    private final S3UploadService s3UploadService;
    private final CockTailIngredientConverter cockTailIngredientConverter;


    public List<CocktailResponse> getAllCocktail() {
        return cocktailConverter.toListResponse(cocktailService.findAllCocktail());
    }

    public CocktailResponse getDetailCocktail(String name) {
        return cocktailConverter.toResponse(cocktailService.findCocktail(name));
    }

    public CocktailResponse saveCocktail(CocktailRequest cocktailRequest, String username) {
        cocktailRequest.setUsername(username);
        FileEntity fileEntity = new FileEntity();
        try {
            fileEntity = s3UploadService.saveFile(cocktailRequest.getImage(), username);
        } catch (IOException e) {
            throw new ApiException(S3_SAVE_ERROR);
        }
        List<CocktailIngredientEntity> cocktailIngredientEntities = cocktailRequest.getListIngredients().stream()
                .map(cockTailIngredientConverter::toEntity).toList();

        CocktailEntity cocktailEntity = cocktailService.saveCocktail(
                cocktailConverter.toEntity(cocktailRequest, fileEntity, cocktailIngredientEntities));
        return cocktailConverter.toResponse(cocktailEntity);
    }
}
