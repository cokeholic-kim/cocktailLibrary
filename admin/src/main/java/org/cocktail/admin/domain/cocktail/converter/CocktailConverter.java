package org.cocktail.admin.domain.cocktail.converter;

import lombok.RequiredArgsConstructor;
import org.cocktail.admin.common.Converter;
import org.cocktail.admin.domain.cocktail.controller.model.CockTailRequest;
import org.cocktail.admin.domain.cocktail.controller.model.CockTailUpdateRequest;
import org.cocktail.db.cocktail.CocktailEntity;
import org.cocktail.db.cocktail.enums.Glass;
import org.cocktail.db.cocktail.enums.Method;
import org.cocktail.db.user.UserEntity;
import org.cocktail.db.user.UserRepository;

@Converter
@RequiredArgsConstructor
public class CocktailConverter {
    private final UserRepository userRepository;

    public CocktailEntity toEntity(CockTailRequest request) {
        UserEntity byId = userRepository.findByEmail(request.getUserId()).orElseThrow(IllegalArgumentException::new);
        return CocktailEntity.builder()
                .user(byId)
                .cocktailName(request.getCocktailName())
                .glass(Glass.fromGlassName(request.getGlass()))
                .garnish(request.getGarnish())
                .ingredients(request.getIngredients())
                .method(Method.valueOf(request.getMethod()))
                .proof(request.getProof())
                .image(request.getImage())
                .description(request.getDescription())
                .build();
    }

    public CocktailEntity toEntity(CockTailUpdateRequest request) {
        UserEntity byId = userRepository.findByEmail(request.getUserId()).orElseThrow(IllegalArgumentException::new);
        return CocktailEntity.builder()
                .id(request.getCockTailId())
                .user(byId)
                .cocktailName(request.getCocktailName())
                .glass(Glass.fromGlassName(request.getGlass()))
                .garnish(request.getGarnish())
                .ingredients(request.getIngredients())
                .method(Method.valueOf(request.getMethod()))
                .proof(request.getProof())
                .image(request.getImage())
                .description(request.getDescription())
                .build();
    }

}
