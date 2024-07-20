package org.cocktail.cocktailappapi.domain.cocktail.controller.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class CocktailRequest {
    private String username;
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
