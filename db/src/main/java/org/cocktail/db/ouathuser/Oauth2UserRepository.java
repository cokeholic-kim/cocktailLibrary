package org.cocktail.db.ouathuser;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Oauth2UserRepository extends JpaRepository<Oauth2UserEntity,Long> {
    Optional<Oauth2UserEntity> findByEmail(String email);
    Optional<Oauth2UserEntity> findByEmailAndDomain(String email,String domain);
}
