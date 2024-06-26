package org.cocktail.admin.domain.cocktail.business;

import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cocktail.admin.common.S3UploadService;
import org.cocktail.admin.domain.cocktail.controller.model.CockTailRequest;
import org.cocktail.admin.domain.cocktail.controller.model.CockTailUpdateRequest;
import org.cocktail.admin.domain.cocktail.converter.CocktailConverter;
import org.cocktail.admin.domain.cocktail.service.CocktailService;
import org.cocktail.db.cocktail.CocktailEntity;
import org.cocktail.db.file.FileEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CocktailBusiness {

    private final CocktailConverter cocktailConverter;
    private final CocktailService cocktailService;
    private final S3UploadService s3UploadService;

    public void register(CockTailRequest request, String userName) throws IOException {
        FileEntity fileEntity = s3UploadService.saveFile(request.getImage(), userName);
        cocktailService.register(cocktailConverter.toEntity(request,fileEntity));
    }
    public List<CocktailEntity> getAllCocktail(){return cocktailService.allCocktail();}
    public CocktailEntity findCocktail(Long id){
        return cocktailService.findCocktail(id);
    }

    public void updateCocktail(CockTailUpdateRequest request, String userName) throws IOException {
        FileEntity file = null;
        if(!request.getImage().isEmpty()){
            file = s3UploadService.updateFile(request.getExistFileName(), request.getImage(), userName);
        }
        CocktailEntity entity = cocktailConverter.toEntity(request,file);
        cocktailService.updateCocktail(entity);
    }

    public void deleteCocktail(Long id,String name){
        s3UploadService.deleteFile(name);
        cocktailService.deleteCocktail(id);
    }

}
