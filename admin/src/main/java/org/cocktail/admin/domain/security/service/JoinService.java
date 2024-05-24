package org.cocktail.admin.domain.security.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.cocktail.db.user.UserEntity;
import org.cocktail.db.user.UserRepository;
import org.cocktail.db.user.enums.UserRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;

    public void register(UserEntity entity) {
        if(userRepository.existsByNickName(entity.getNickName()) ){
            throw new IllegalArgumentException("해당유저정보가 존재합니다.");
        }
        userRepository.save(entity);
    }

    public List<UserEntity> findUsers() {
        return userRepository.findAll();
    }
    @Transactional
    public void changeRole(Long id, String role) {
        UserEntity originEntity = userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        Optional<UserEntity> byId = userRepository.findById(id);
        originEntity.setRole(UserRole.valueOf(role));
    }
}
