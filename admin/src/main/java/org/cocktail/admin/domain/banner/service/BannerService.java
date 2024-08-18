package org.cocktail.admin.domain.banner.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.cocktail.admin.common.ErrorCodeFile;
import org.cocktail.admin.common.S3UploadService;
import org.cocktail.admin.domain.banner.validate.BannerValidate;
import org.cocktail.common.exception.ApiException;
import org.cocktail.db.banner.BannerEntity;
import org.cocktail.db.banner.BannerRepository;
import org.cocktail.db.file.FileEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class BannerService {

    private final BannerRepository bannerRepository;
    private final BannerValidate bannerValidate;
    private final S3UploadService s3UploadService;

    public List<BannerEntity> getAllBanner(){
        List<BannerEntity> all = bannerRepository.findAllByOrderByOrderAsc();
        return all;
    }
    @Transactional
    public BannerEntity saveBanner(BannerEntity entity) {
        //TODO 저장시 s3에 저장하는 로직 서비스로 이관 서비스에서 저장후 file set 에러발생시 파일 삭제하도록 처리.
        int order = bannerRepository.findAll().size();
        entity.setOrder(order + 1);
        return bannerRepository.save(entity);
    }

    @Transactional
    public void delete(String name) {
        BannerEntity bannerEntity = bannerValidate.validateFindBanner(bannerRepository.findByTitle(name));
        s3UploadService.deleteFile(bannerEntity.getFile().getFileName());
        bannerRepository.delete(bannerEntity);
        //삭제후 order 다시 입력
        List<BannerEntity> all = bannerRepository.findAllByOrderByOrderAsc();
        int order = all.size();
        for(int i =0;i < order;i++){
            all.get(i).setOrder(i+1);
        }

        bannerRepository.saveAll(all);
    }

    public BannerEntity findBanner(String name) {
        Optional<BannerEntity> byTitle = bannerRepository.findByTitle(name);
        return bannerValidate.validateFindBanner(byTitle);
    }
    @Transactional
    public void updateBanner(BannerEntity banner, MultipartFile image, String userName) {

        BannerEntity oldBanner = bannerValidate.validateFindBanner(bannerRepository.findByTitle(banner.getTitle()));
        FileEntity file;
        if(!image.isEmpty()){
            try {
                file = s3UploadService.saveFile(image, userName);
                oldBanner.setFile(file);
            } catch (IOException e) {
                throw new ApiException(ErrorCodeFile.FAIL_SAVE);
            }
        }

        oldBanner.setTitle(banner.getTitle());
        oldBanner.setSrc(banner.getSrc());
        //TODO:: order가 겹치는 번호가있을때의 처리 필요.
        if(banner.getOrder() != null){
            oldBanner.setOrder(banner.getOrder());
        }
    }
}
