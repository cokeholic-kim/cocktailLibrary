package org.cocktail.admin.domain.security.converter;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.cocktail.admin.common.Converter;
import org.cocktail.admin.domain.security.controller.model.UserRequest;
import org.cocktail.admin.domain.security.controller.model.UserResponse;
import org.cocktail.admin.domain.security.controller.model.UserUpdateRequest;
import org.cocktail.db.user.UserEntity;
import org.cocktail.db.user.enums.UserRole;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Converter
@RequiredArgsConstructor
public class JoinConverter {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserEntity toEntity(UserRequest rq) {
        return Optional.ofNullable(
                UserEntity.builder()
                        .nickName(rq.getNickName())
                        .email(rq.getEmail())
                        .password(bCryptPasswordEncoder.encode(rq.getPassword()))
                        .role(rq.getRole())
                        .build()
        ).orElseThrow(IllegalArgumentException::new);
    }





    public UserResponse toResponse(UserEntity entity) {
        return Optional.ofNullable(
                UserResponse.builder()
                        .id(entity.getId())
                        .nickName(entity.getNickName())
                        .email(entity.getEmail())
                        .role(entity.getRole())
                        .build()
        ).orElseThrow(IllegalArgumentException::new);
    }
}
