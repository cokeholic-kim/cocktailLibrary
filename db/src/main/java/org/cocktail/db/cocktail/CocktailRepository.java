package org.cocktail.db.cocktail;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CocktailRepository extends JpaRepository<CocktailEntity,Long> {
}
