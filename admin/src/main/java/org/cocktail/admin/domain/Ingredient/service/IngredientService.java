package org.cocktail.admin.domain.Ingredient.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cocktail.admin.common.S3UploadService;
import org.cocktail.admin.common.UploadService;
import org.cocktail.db.ingredient.IngredientEntity;
import org.cocktail.db.ingredient.IngredientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;


    @Transactional
    public void register(IngredientEntity entity) {
        //엔티티를 저장하고 이미지 데이터를 경로에 저장
        ingredientRepository.save(entity);
    }

    @Transactional
    public void update(IngredientEntity newIngredient) {
        IngredientEntity old = ingredientRepository.findById(newIngredient.getId())
                .orElseThrow(IllegalArgumentException::new);
        BeanUtils.copyProperties(newIngredient, old, "id");
    }

    @Transactional
    public void delete(String name) {
        IngredientEntity ingredientEntity = ingredientRepository.findByName(name)
                .orElseThrow(IllegalArgumentException::new);
        ingredientRepository.delete(ingredientEntity);
    }

    public List<IngredientEntity> findALl() {
        return ingredientRepository.findAll();
    }

    public IngredientEntity findByName(String name) {
        return ingredientRepository.findByName(name).orElseThrow(IllegalArgumentException::new);
    }
}
