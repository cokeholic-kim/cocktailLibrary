package org.cocktail.cocktailappapi.domain.user.controller.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class JoinRequest {
    @Email
    @NotBlank
    private final String email;
    @NotBlank
    @Size(min = 8, max = 20)
    @Pattern(regexp =  "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]*$",message = "비밀번호는 최소 한개의 소문자 ,대문자 ,숫자 ,특수문자를 포함해야 합니다.")
    private final String password;
    @NotBlank
    @Size(min = 3, max = 20,message = "닉네임의 길이는 3 ~ 20 글자입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9가-힣_-]+$",message = "닉네임은 한글,영어,숫자 특수문자 _ - 만 입력가능합니다")
    private final String name;
}
