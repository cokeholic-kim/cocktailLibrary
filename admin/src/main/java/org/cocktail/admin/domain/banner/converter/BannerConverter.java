package org.cocktail.admin.domain.banner.converter;

import lombok.RequiredArgsConstructor;
import org.cocktail.admin.domain.banner.controller.model.BannerRequest;
import org.cocktail.admin.domain.banner.controller.model.BannerResponse;
import org.cocktail.common.Converter;
import org.cocktail.db.banner.BannerEntity;
import org.cocktail.db.file.FileEntity;

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

    public BannerEntity toEntity(BannerRequest bannerRequest, FileEntity fileEntity) {
        return BannerEntity.builder()
                .file(fileEntity)
                .title(bannerRequest.getBannerTitle())
                .src(bannerRequest.getBannerSrc())
                .build();
    }

    public BannerEntity toEntity(BannerRequest bannerRequest) {
        return BannerEntity.builder()
                .title(bannerRequest.getBannerTitle())
                .src(bannerRequest.getBannerSrc())
                .order(bannerRequest.getBannerOrder())
                .build();
    }
}
