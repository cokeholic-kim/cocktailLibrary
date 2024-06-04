package org.cocktail.db.ingredient;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<IngredientEntity,Long> {
    Optional<IngredientEntity> findByName(String name);
}
