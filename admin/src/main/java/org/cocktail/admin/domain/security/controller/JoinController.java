package org.cocktail.admin.domain.security.controller;

import lombok.RequiredArgsConstructor;
import org.cocktail.admin.domain.security.business.JoinBusiness;
import org.cocktail.admin.domain.security.controller.model.UserRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class JoinController {

    private final JoinBusiness joinBusiness;

    @GetMapping("/join")
    public String join() {
        return "login/join";
    }

    @PostMapping("/joinProc")
    public String joinProc(UserRequest userRequest) {

        System.out.println("userRequest = " + userRequest);
        joinBusiness.register(userRequest);

        return "redirect:/login";
    }
}

