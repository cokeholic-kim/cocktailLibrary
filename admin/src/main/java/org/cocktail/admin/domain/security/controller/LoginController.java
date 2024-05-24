package org.cocktail.admin.domain.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login() {
        System.out.println("AdminController.login");
        return "login/index";
    }

}
