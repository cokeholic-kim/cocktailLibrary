package org.cocktail.cocktailappapi.domain.user.business;

import lombok.RequiredArgsConstructor;
import org.cocktail.cocktailappapi.domain.user.controller.model.JoinRequest;
import org.cocktail.cocktailappapi.domain.user.converter.UserConverter;
import org.cocktail.cocktailappapi.domain.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

@Service
@RequiredArgsConstructor
public class UserBusiness {
    private final UserService userService;
    private final UserConverter userConverter;

    public void join(JoinRequest joinRequest) {
        userService.join(userConverter.toEntity(joinRequest));
    }

    public void catchError(Errors errors) {
        userService.catchError(errors);
    }
}
