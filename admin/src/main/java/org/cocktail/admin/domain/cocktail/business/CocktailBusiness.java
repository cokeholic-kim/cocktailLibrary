package org.cocktail.admin.domain.cocktail.business;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cocktail.admin.domain.cocktail.controller.model.CockTailRequest;
import org.cocktail.admin.domain.cocktail.controller.model.CockTailUpdateRequest;
import org.cocktail.admin.domain.cocktail.controller.model.CocktailRequests;
import org.cocktail.admin.domain.cocktail.converter.CocktailConverter;
import org.cocktail.admin.domain.cocktail.service.CocktailService;
import org.cocktail.db.cocktail.CocktailEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CocktailBusiness {

    private final CocktailConverter cocktailConverter;
    private final CocktailService cocktailService;

    public void register(CockTailRequest request){
        cocktailService.register(cocktailConverter.toEntity(request),request.getImage());
    }
    public List<CocktailEntity> getAllCocktail(){return cocktailService.allCocktail();}
    public CocktailEntity findCocktail(Long id){
        return cocktailService.findCocktail(id);
    }

    public void updateCocktail(CockTailUpdateRequest request){
        cocktailService.updateCocktail(cocktailConverter.toEntity(request));
    }

    public void deleteCocktail(Long id){
        cocktailService.deleteCocktail(id);
    }

    public void registerAll(CocktailRequests requests) {
        List<CocktailEntity> list = requests.getCockTailRequestList().stream()
                .map(cocktailConverter::toEntity)
                .toList();
        cocktailService.registerAll(list);
    }
}
