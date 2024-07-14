package org.cocktail.cocktailappapi.domain.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.cocktail.cocktailappapi.domain.user.business.UserBusiness;
import org.cocktail.cocktailappapi.domain.user.controller.model.JoinRequest;
import org.cocktail.common.api.Api;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserBusiness userBusiness;


    @PostMapping("/join")
    public Api<String> join(@Valid @RequestBody JoinRequest joinRequest, Errors errors) {
        if(errors.hasErrors()){
            userBusiness.catchError(errors);
        }
        userBusiness.join(joinRequest);
        return Api.OK("회원가입되었습니다. enjoy our service");
    }
}
