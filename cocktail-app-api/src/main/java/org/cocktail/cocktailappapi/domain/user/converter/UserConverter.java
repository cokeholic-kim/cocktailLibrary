package org.cocktail.cocktailappapi.domain.user.converter;

import lombok.RequiredArgsConstructor;
import org.cocktail.cocktailappapi.domain.user.controller.model.JoinRequest;
import org.cocktail.common.Converter;
import org.cocktail.db.user.UserEntity;
import org.cocktail.db.user.enums.UserRole;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Converter
@RequiredArgsConstructor
public class UserConverter {
    private final BCryptPasswordEncoder passwordEncoder;
    public UserEntity toEntity(JoinRequest joinRequest){
        return UserEntity.builder()
                .email(joinRequest.getEmail())
                .password(passwordEncoder.encode(joinRequest.getPassword()))
                .nickName(joinRequest.getName())
                .role(UserRole.ROLE_USER)
                .build();
    }
}
