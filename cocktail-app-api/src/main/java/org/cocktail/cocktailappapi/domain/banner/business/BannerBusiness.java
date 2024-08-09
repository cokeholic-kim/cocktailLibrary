package org.cocktail.cocktailappapi.domain.banner.business;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cocktail.cocktailappapi.domain.banner.business.model.BannerResponse;
import org.cocktail.cocktailappapi.domain.banner.converter.BannerConverter;
import org.cocktail.cocktailappapi.domain.banner.service.BannerService;
import org.cocktail.db.banner.BannerEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BannerBusiness {

    private final BannerConverter bannerConverter;
    private final BannerService bannerService;

    public List<BannerResponse> getAllBanner() {
        List<BannerEntity> allBanner = bannerService.getAllBanner();
        return allBanner.stream().map(bannerConverter::toResponse)
                .toList();
    }
}
