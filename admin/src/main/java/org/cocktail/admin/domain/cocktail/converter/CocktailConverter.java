package org.cocktail.admin.domain.cocktail.converter;

import static org.cocktail.admin.common.UploadService.createFileName;

import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.cocktail.admin.common.Converter;
import org.cocktail.admin.domain.cocktail.controller.model.CockTailRequest;
import org.cocktail.admin.domain.cocktail.controller.model.CockTailUpdateRequest;
import org.cocktail.admin.domain.cocktail.controller.model.CocktailResponse;
import org.cocktail.db.CocktailIngredient.CocktailIngredientEntity;
import org.cocktail.db.cocktail.CocktailEntity;
import org.cocktail.db.cocktail.enums.Glass;
import org.cocktail.db.cocktail.enums.Method;
import org.cocktail.db.file.FileEntity;
import org.cocktail.db.file.FileRepository;
import org.cocktail.db.user.UserEntity;
import org.cocktail.db.user.UserRepository;

@Converter
@RequiredArgsConstructor
public class CocktailConverter {
    private final UserRepository userRepository;
    private final FileRepository fileRepository;

    public CocktailEntity toEntity(CockTailRequest request, FileEntity file, List<CocktailIngredientEntity> cocktailIngredientEntity) {
        UserEntity byId = userRepository.findByEmail(request.getUserId()).orElseThrow(IllegalArgumentException::new);
        CocktailEntity cocktailEntity  = CocktailEntity.builder()
                .user(byId)
                .cocktailName(request.getCocktailName())
                .glass(Glass.fromGlassName(request.getGlass()))
                .garnish(request.getGarnish())
                .ingredients(request.getIngredients().toString())
                .method(Method.valueOf(request.getMethod()))
                .proof(request.getProof())
                .description(request.getDescription())
                .file(file)
                .build();

        for (CocktailIngredientEntity ingredient : cocktailIngredientEntity) {
            ingredient.setCocktail(cocktailEntity);
        }

        cocktailEntity.setCocktailIngredients(cocktailIngredientEntity);
        return cocktailEntity;
    }

    public CocktailEntity toEntity(CockTailUpdateRequest request,FileEntity file) {
        UserEntity byId = userRepository.findByEmail(request.getUserId()).orElseThrow(IllegalArgumentException::new);

        if (Objects.isNull(file)) {
            file = fileRepository.findByFileName(request.getExistFileName())
                    .orElseThrow(IllegalArgumentException::new);
        }

        return CocktailEntity.builder()
                .id(request.getCockTailId())
                .user(byId)
                .cocktailName(request.getCocktailName())
                .glass(Glass.fromGlassName(request.getGlass()))
                .garnish(request.getGarnish())
                .ingredients(request.getIngredients())
                .method(Method.valueOf(request.getMethod()))
                .proof(request.getProof())
                .description(request.getDescription())
                .file(file)
                .build();
    }

    public CocktailResponse toResponse(CocktailEntity entity){
            return CocktailResponse.builder()
                    .cocktailIngredients(entity.getCocktailIngredients())
                    .user(entity.getUser())
                    .id(entity.getId())
                    .cocktailName(entity.getCocktailName())
                    .ingredients(entity.getIngredients())
                    .proof(entity.getProof())
                    .glass(entity.getGlass())
                    .method(entity.getMethod())
                    .garnish(entity.getGarnish())
                    .description(entity.getDescription())
                    .file(entity.getFile())
                    .build();
    }

}
