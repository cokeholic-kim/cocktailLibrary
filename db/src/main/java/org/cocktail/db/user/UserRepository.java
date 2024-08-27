package org.cocktail.db.user;

import java.util.Optional;
import org.cocktail.db.user.enums.LoginMethod;
import org.cocktail.db.user.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Boolean existsByNickName(String nickName);
    Boolean existsByEmail(String email);

    Optional<UserEntity> findByEmail(String Email);
    Optional<UserEntity> findByIdAndEmail(Long id, String email);

    Optional<UserEntity> findByNickName(String nickName);

    Optional<UserEntity> findByEmailAndRoleAndLoginMethod(String email, UserRole role, LoginMethod loginMethod);
}
