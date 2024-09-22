package org.cocktail.cocktailappapi.oauth.dto;

import lombok.Builder;
import lombok.Data;
import org.cocktail.db.user.enums.UserRole;

@Data
@Builder
public class UserDto {

    private UserRole role;
    private String name;
    private String username;
    private String email;
}
