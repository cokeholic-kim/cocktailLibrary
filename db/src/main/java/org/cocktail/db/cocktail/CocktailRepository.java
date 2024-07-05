package org.cocktail.db.cocktail;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CocktailRepository extends JpaRepository<CocktailEntity,Long> {
    Optional<CocktailEntity> findByCocktailName(String name);
}
