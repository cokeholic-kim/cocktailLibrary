package org.cocktail.admin.domain.banner.business;

import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;

import org.cocktail.admin.common.ErrorCodeFile;
import org.cocktail.admin.common.S3UploadService;
import org.cocktail.admin.domain.banner.controller.model.BannerRequest;
import org.cocktail.admin.domain.banner.controller.model.BannerResponse;
import org.cocktail.admin.domain.banner.converter.BannerConverter;
import org.cocktail.admin.domain.banner.service.BannerService;
import org.cocktail.common.exception.ApiException;
import org.cocktail.db.banner.BannerEntity;
import org.cocktail.db.file.FileEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BannerBusiness {

    private final BannerConverter bannerConverter;
    private final BannerService bannerService;
    private final S3UploadService s3UploadService;

    public List<BannerResponse> getAllBanner() {
        List<BannerEntity> allBanner = bannerService.getAllBanner();
        return allBanner.stream().map(bannerConverter::toResponse)
                .toList();
    }

    public BannerResponse saveBanner(BannerRequest bannerRequest, String userName) {
        FileEntity fileEntity = new FileEntity();
        try {
            fileEntity = s3UploadService.saveFile(bannerRequest.getImage(), userName);
        } catch (IOException e) {
            throw new ApiException(ErrorCodeFile.FAIL_SAVE);
        }
        BannerEntity bannerEntity = bannerService.saveBanner(bannerConverter.toEntity(bannerRequest, fileEntity));
        return bannerConverter.toResponse(bannerEntity);
    }


    public void deleteBanner(String name) {
        bannerService.delete(name);
    }

    public BannerResponse findBanner(String name) {
        BannerEntity banner = bannerService.findBanner(name);
        return bannerConverter.toResponse(banner);
    }

    public void updateBanner(BannerRequest bannerRequest, String userName) {
        BannerEntity banner = bannerConverter.toEntity(bannerRequest);
        bannerService.updateBanner(banner,bannerRequest.getImage(),userName);
    }
}
