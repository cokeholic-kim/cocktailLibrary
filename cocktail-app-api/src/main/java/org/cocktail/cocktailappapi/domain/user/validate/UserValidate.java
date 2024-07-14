package org.cocktail.cocktailappapi.domain.user.validate;

import static org.cocktail.cocktailappapi.domain.user.validate.ErrorCodeUser.REDUNDANT_EMAIL;
import static org.cocktail.cocktailappapi.domain.user.validate.ErrorCodeUser.REDUNDANT_NICKNAME;

import org.cocktail.common.exception.ApiException;
import org.cocktail.db.user.UserEntity;
import org.cocktail.db.user.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserValidate {

    public void validateJoin(UserEntity userEntity, UserRepository userRepository){
        if(userRepository.existsByEmail(userEntity.getEmail())){
            throw new ApiException(REDUNDANT_EMAIL);
        }
        if(userRepository.existsByNickName(userEntity.getNickName())){
            throw new ApiException(REDUNDANT_NICKNAME);
        }
    }
}
