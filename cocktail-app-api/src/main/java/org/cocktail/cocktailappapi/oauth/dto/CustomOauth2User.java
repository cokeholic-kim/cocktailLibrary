package org.cocktail.cocktailappapi.oauth.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustomOauth2User implements OAuth2User {

    private final UserDto userDto;

    public CustomOauth2User(UserDto userDto){
        this.userDto = userDto;
    }
    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return String.valueOf(userDto.getRole());
            }
        });
        return collection;
    }

    @Override
    public String getName() {
        return userDto.getEmail();
    }

    public String getUserName(){
        return userDto.getUsername();
    }
}
