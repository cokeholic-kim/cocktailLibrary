package org.cocktail.admin.domain.security.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cocktail.db.user.enums.UserRole;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String nickName;
    private String email;
    private UserRole role;
}
