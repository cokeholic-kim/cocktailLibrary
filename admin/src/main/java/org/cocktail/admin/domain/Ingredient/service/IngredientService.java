package org.cocktail.admin.domain.Ingredient.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cocktail.admin.common.UploadService;
import org.cocktail.db.ingredient.IngredientEntity;
import org.cocktail.db.ingredient.IngredientRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


@Service
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final UploadService uploadService;


    @Transactional
    public void register(IngredientEntity entity, MultipartFile image){
        uploadService.saveFile(image,entity.getImage());
        //엔티티를 저장하고 이미지 데이터를 경로에 저장
        ingredientRepository.save(entity);
    }
    @Transactional
    public void update(Long id){
        IngredientEntity ingredientEntity = ingredientRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        //조회하고 원하는값을 변경 request객체를 생성하고 해당 객체값을 세팅해서 변경예정
    }

    public void delete(Long id){
        ingredientRepository.deleteById(id);
    }

    public List<IngredientEntity> findALl(){
        return ingredientRepository.findAll();
    }
}
