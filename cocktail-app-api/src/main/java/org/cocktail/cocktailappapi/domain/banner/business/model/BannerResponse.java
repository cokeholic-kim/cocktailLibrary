package org.cocktail.cocktailappapi.domain.banner.business.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BannerResponse {
    private String imagePath;
    private String title;
    private String src;
    private Integer order;
}
