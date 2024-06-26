package org.cocktail.admin.domain.cocktail.controller.model;

import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Data
@Getter
public class CockTailUpdateRequest {
    private Long cockTailId;
    private String userId;
    private String cocktailName;
    private String ingredients;
    private double proof;
    private String glass;
    private String method;
    private String garnish;
    private MultipartFile image;
    private String description;
    private String existFileName;
}
