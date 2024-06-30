package org.cocktail.admin.domain.cocktail.controller.model;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.cocktail.db.CocktailIngredient.CocktailIngredientEntity;
import org.cocktail.db.cocktail.enums.Glass;
import org.cocktail.db.cocktail.enums.Method;
import org.cocktail.db.file.FileEntity;
import org.cocktail.db.user.UserEntity;

@Data
@Builder
public class CocktailResponse {
    private Long id;
    private UserEntity user;
    private String cocktailName;
    private String ingredients;
    private double proof;
    private Glass glass;
    private Method method;

    private String garnish;
    private String description;

    private FileEntity file;

    private List<CocktailIngredientEntity> cocktailIngredients;
}
