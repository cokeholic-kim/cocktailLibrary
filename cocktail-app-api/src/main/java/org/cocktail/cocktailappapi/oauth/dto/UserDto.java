package org.cocktail.cocktailappapi.oauth.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private String role;
    private String name;
    private String username;
}
