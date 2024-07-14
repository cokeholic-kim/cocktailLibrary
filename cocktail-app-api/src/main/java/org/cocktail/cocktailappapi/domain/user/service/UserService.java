package org.cocktail.cocktailappapi.domain.user.service;

import static org.cocktail.cocktailappapi.domain.user.validate.ErrorCodeUser.REDUNDANT_EMAIL;
import static org.cocktail.cocktailappapi.domain.user.validate.ErrorCodeUser.REDUNDANT_NICKNAME;

import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.cocktail.cocktailappapi.domain.user.validate.ErrorCodeUser;
import org.cocktail.cocktailappapi.domain.user.validate.UserValidate;
import org.cocktail.common.exception.ApiException;
import org.cocktail.db.user.UserEntity;
import org.cocktail.db.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserValidate userValidate;
    public void join(UserEntity userEntity){
        userValidate.validateJoin(userEntity,userRepository);
        userRepository.save(userEntity);
    }

    public void catchError(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }

        throw new ApiException(ErrorCodeUser.JOIN_ERROR,validatorResult.toString());
    }
}
