package org.cocktail.admin.domain.cocktail.controller.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Data
@Getter
public class CockTailRequest {
    private String userId;
    private String cocktailName;
    private List<String> ingredients;
    private double proof;
    private String glass;
    private String method;
    private String garnish;
    private MultipartFile image;
    private String description;
    public List<CocktailIngredientRequest> getListIngredients() {
        ObjectMapper objectMapper = new ObjectMapper();

        return this.ingredients.stream().map(json -> {
            try {
                return objectMapper.readValue(json, CocktailIngredientRequest.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Error parsing JSON: " + e.getMessage());
            }
        }).collect(Collectors.toList());
    }

}



