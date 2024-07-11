package org.cocktail.cocktailappapi.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.cocktail.cocktailappapi.domain.user.controller.model.CustomUserDetails;
import org.cocktail.db.user.UserEntity;
import org.cocktail.db.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity byEmail = userRepository.findByEmail(username).orElseThrow(IllegalArgumentException::new);
        String encodePassword = passwordEncoder.encode(byEmail.getPassword());

        return new CustomUserDetails(byEmail);
    }
}
