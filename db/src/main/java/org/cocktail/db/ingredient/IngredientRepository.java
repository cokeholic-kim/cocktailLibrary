package org.cocktail.db.ingredient;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<IngredientEntity,Long> {
    Optional<IngredientEntity> findByName(String name);
    List<IngredientEntity> findAllByName(String name);

    List<IngredientEntity> findAllByNameIn(List<String> name);
}
