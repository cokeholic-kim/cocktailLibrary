package org.cocktail.admin.domain.cocktail.controller.model;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CockTailRequest {
    private String userId;
    private String cocktailName;
    private String ingredients;
    private double proof;
    private String glass;
    private String method;
    private String garnish;
    private String image;
    private String description;
}
