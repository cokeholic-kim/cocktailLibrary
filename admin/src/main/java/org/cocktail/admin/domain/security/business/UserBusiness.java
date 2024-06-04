package org.cocktail.admin.domain.security.business;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cocktail.admin.domain.security.controller.model.UserRequest;
import org.cocktail.admin.domain.security.controller.model.UserResponse;
import org.cocktail.admin.domain.security.controller.model.UserUpdateRequest;
import org.cocktail.admin.domain.security.converter.JoinConverter;
import org.cocktail.admin.domain.security.service.UserService;
import org.cocktail.db.user.UserEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserBusiness {

    private final UserService userService;
    private final JoinConverter converter;

    public List<UserEntity> readAllUser(){
        return userService.readAllUser();
    }

    public void deleteUser(Long id, String email){
        userService.deleteUser(id,email);
    }

    public UserResponse userDetail(Long id){
        return converter.toResponse(userService.detailUser(id));
    }

    public void userUpdate(UserUpdateRequest userRequest) {
        userService.updateUser(userRequest);
    }
}
