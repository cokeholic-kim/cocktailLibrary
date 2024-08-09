package org.cocktail.db.banner;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerRepository extends JpaRepository<BannerEntity,Long> {
    List<BannerEntity> findAllByOrderByOrderAsc();
    Optional<BannerEntity> findByTitle(String title);
}
