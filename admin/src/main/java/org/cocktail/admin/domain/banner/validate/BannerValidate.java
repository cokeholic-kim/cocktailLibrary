package org.cocktail.admin.domain.banner.validate;

import java.util.List;
import java.util.Optional;
import org.cocktail.common.exception.ApiException;
import org.cocktail.db.banner.BannerEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BannerValidate {

    public void validateListBanner(List<BannerEntity> bannerEntityList){
        if(bannerEntityList.isEmpty()){
            throw new ApiException(ErrorCodeBanner.EMPTY_BANNER);
        }
    }
    @Transactional
    public BannerEntity validateFindBanner(Optional<BannerEntity> bannerEntity){
        return bannerEntity.orElseThrow(() -> new ApiException(ErrorCodeBanner.NULL_BANNER));
    }
}
