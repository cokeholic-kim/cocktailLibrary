package org.cocktail.admin.domain.security.service;

import lombok.RequiredArgsConstructor;
import org.cocktail.admin.domain.security.CustomUserDetails;
import org.cocktail.db.user.UserEntity;
import org.cocktail.db.user.UserRepository;
import org.cocktail.db.user.enums.LoginMethod;
import org.cocktail.db.user.enums.UserRole;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmailAndRoleAndLoginMethod(username, UserRole.ADMIN, LoginMethod.APP).orElseThrow(IllegalArgumentException::new);
        return new CustomUserDetails(userEntity);
    }
}
