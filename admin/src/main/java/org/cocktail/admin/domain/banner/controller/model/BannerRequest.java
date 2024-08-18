package org.cocktail.admin.domain.banner.controller.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BannerRequest {
    private final String bannerTitle;
    private final String bannerSrc;
    private final MultipartFile image;
    private final Integer bannerOrder;
}
