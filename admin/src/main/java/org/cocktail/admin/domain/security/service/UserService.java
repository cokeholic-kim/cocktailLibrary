package org.cocktail.admin.domain.security.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cocktail.admin.domain.security.controller.model.UserUpdateRequest;
import org.cocktail.db.user.UserEntity;
import org.cocktail.db.user.UserRepository;
import org.cocktail.db.user.enums.UserRole;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserEntity> readAllUser(){
        return userRepository.findAll();
    }

    public void deleteUser(Long id, String email){
        UserEntity byId = userRepository.findByIdAndEmail(id,email).orElseThrow(IllegalArgumentException::new);
        userRepository.delete(byId);
    }

    public UserEntity detailUser(Long id){
        return userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Transactional
    public void updateUser(UserUpdateRequest userUpdateRequest){
        UserEntity userEntity = userRepository.findById(userUpdateRequest.getId()).orElseThrow(IllegalArgumentException::new);
        userEntity.setNickName(userUpdateRequest.getNickName());
        userEntity.setRole(UserRole.valueOf(userUpdateRequest.getRole()));
    }
}
