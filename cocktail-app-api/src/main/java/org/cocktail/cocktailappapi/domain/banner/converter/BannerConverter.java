package org.cocktail.cocktailappapi.domain.banner.converter;

import lombok.RequiredArgsConstructor;
import org.cocktail.cocktailappapi.domain.banner.business.model.BannerResponse;
import org.cocktail.common.Converter;
import org.cocktail.db.banner.BannerEntity;

@Converter
@RequiredArgsConstructor
public class BannerConverter {

    public BannerResponse toResponse(BannerEntity bannerEntity){
        return BannerResponse.builder()
                .imagePath(bannerEntity.getFile().getFilePath())
                .title(bannerEntity.getTitle())
                .src(bannerEntity.getSrc())
                .order(bannerEntity.getOrder())
                .build();
    }
}
