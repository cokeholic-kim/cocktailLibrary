package org.cocktail.db.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Boolean existsByNickName(String nickName);
    Boolean existsByEmail(String email);

    Optional<UserEntity> findByEmail(String Email);
}
