package org.cocktail.admin.domain.security.controller.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import org.cocktail.db.user.enums.UserRole;

@Data
@Getter
public class UserUpdateRequest {
    @NotBlank
    private String nickName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private Long id;

    @NotNull
    private String role;
}
