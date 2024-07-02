package org.cocktail.admin.domain.cocktail.controller.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Data
@Getter
public class CockTailUpdateRequest {
    //TODO 예외처리 id나 특정값은 받지않게 수정.
    private Long cockTailId;
    private String userId;
    private String cocktailName;
    private double proof;
    private String glass;
    private String method;
    private String garnish;
    private MultipartFile image;
    private String description;
    private String existFileName;
    private List<String> ingredientName;
    private List<Double> ingredientVolume;
    private List<String> ingredientUnit;

    public List<CocktailIngredientRequest> getListIngredients(){
        ArrayList<CocktailIngredientRequest> cocktailIngredientRequests = new ArrayList<CocktailIngredientRequest>();
        for (int i = 0; i < ingredientName.size(); i++) {
            CocktailIngredientRequest build = CocktailIngredientRequest.builder()
                    .name(this.ingredientName.get(i))
                    .volume(this.ingredientVolume.get(i))
                    .unit(this.ingredientUnit.get(i))
                    .build();
            cocktailIngredientRequests.add(build);
        }
        return cocktailIngredientRequests;
    }
}
