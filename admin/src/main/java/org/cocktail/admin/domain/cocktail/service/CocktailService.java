package org.cocktail.admin.domain.cocktail.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cocktail.admin.common.UploadService;
import org.cocktail.db.cocktail.CocktailEntity;
import org.cocktail.db.cocktail.CocktailRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class CocktailService {
    private final CocktailRepository cocktailRepository;
    private final UploadService uploadService;

    @Transactional
    public void register(CocktailEntity cocktailEntity, MultipartFile image) {
        uploadService.saveFile(image, cocktailEntity.getImage());
        cocktailRepository.save(cocktailEntity);
    }

    public List<CocktailEntity> allCocktail() {
        return cocktailRepository.findAll();
    }

    public CocktailEntity findCocktail(Long id) {
        return cocktailRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Transactional
    public void updateCocktail(CocktailEntity newCocktail) {
        CocktailEntity old = cocktailRepository.findById(newCocktail.getId())
                .orElseThrow(IllegalArgumentException::new);
        BeanUtils.copyProperties(newCocktail, old, "id");
    }

    public void deleteCocktail(Long id) {
        CocktailEntity byId = cocktailRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        cocktailRepository.delete(byId);
    }

    public void registerAll(List<CocktailEntity> list) {
        cocktailRepository.saveAll(list);
    }
}
