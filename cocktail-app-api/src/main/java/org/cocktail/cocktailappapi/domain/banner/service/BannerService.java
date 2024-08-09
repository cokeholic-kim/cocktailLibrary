package org.cocktail.cocktailappapi.domain.banner.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cocktail.cocktailappapi.domain.banner.validate.BannerValidate;
import org.cocktail.db.banner.BannerEntity;
import org.cocktail.db.banner.BannerRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BannerService {

    private final BannerRepository bannerRepository;
    private final BannerValidate bannerValidate;

    public List<BannerEntity> getAllBanner(){
        List<BannerEntity> all = bannerRepository.findAllByOrderByOrderAsc();
        bannerValidate.validateListBanner(all);
        return all;
    }
}
