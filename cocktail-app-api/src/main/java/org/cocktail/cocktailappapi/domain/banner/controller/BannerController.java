package org.cocktail.cocktailappapi.domain.banner.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cocktail.cocktailappapi.domain.banner.business.BannerBusiness;
import org.cocktail.cocktailappapi.domain.banner.business.model.BannerResponse;
import org.cocktail.common.api.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("banner")
@RequiredArgsConstructor
public class BannerController {

    private final BannerBusiness bannerBusiness;
    @GetMapping("getAllBanner")
    public Api<List<BannerResponse>> getAllBanner(){
        List<BannerResponse> allBanner = bannerBusiness.getAllBanner();
        return Api.OK(allBanner);
    }
}
