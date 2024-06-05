package org.cocktail.admin.domain.cocktail.controller.model;

import jakarta.persistence.Column;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CockTailRequest {
    private String userId;
    private String cocktailName;
    private String ingredients;
    private double proof;
    private String glass;
    private String method;
    private String garnish;
    private MultipartFile image;
    private String description;
}
