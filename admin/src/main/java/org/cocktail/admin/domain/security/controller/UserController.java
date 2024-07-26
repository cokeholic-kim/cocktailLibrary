package org.cocktail.admin.domain.security.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cocktail.admin.domain.security.business.UserBusiness;
import org.cocktail.admin.domain.security.controller.model.UserRequest;
import org.cocktail.admin.domain.security.controller.model.UserResponse;
import org.cocktail.admin.domain.security.controller.model.UserUpdateRequest;
import org.cocktail.db.user.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserBusiness userBusiness;

    @GetMapping("/page")
    public String userPage(Model model){
        List<UserEntity> userEntities = userBusiness.readAllUser();
        model.addAttribute("allUser", userEntities);
        model.addAttribute("pagename","user");

        return "/user/index";
    }

    @GetMapping("/delete/{id}/{email}")
    @ResponseBody
    public ResponseEntity<Void> deleteUser(@PathVariable Long id,@PathVariable String email){
        System.out.println("email = " + email);
        System.out.println("id = " + id);

        userBusiness.deleteUser(id,email);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/detail/{id}")
    public String userDetail(@PathVariable Long id, Model model){
        UserResponse userResponse = userBusiness.userDetail(id);
        model.addAttribute("user",userResponse);
        return "/user/userDetail";
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity<Void> userUpdate(UserUpdateRequest userRequest){
        userBusiness.userUpdate(userRequest);
        return ResponseEntity.noContent().build();
    }
}
