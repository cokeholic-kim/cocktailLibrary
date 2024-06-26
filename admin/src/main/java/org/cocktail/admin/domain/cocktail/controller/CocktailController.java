package org.cocktail.admin.domain.cocktail.controller;

import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cocktail.admin.domain.cocktail.business.CocktailBusiness;
import org.cocktail.admin.domain.cocktail.controller.model.CockTailRequest;
import org.cocktail.admin.domain.cocktail.controller.model.CockTailUpdateRequest;
import org.cocktail.admin.domain.cocktail.controller.model.CocktailRequests;
import org.cocktail.db.cocktail.CocktailEntity;
import org.cocktail.db.cocktail.enums.Glass;
import org.cocktail.db.cocktail.enums.Method;
import org.springframework.boot.Banner.Mode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cockTail")
@RequiredArgsConstructor
@Slf4j
public class CocktailController {

    private final CocktailBusiness cocktailBusiness;

    @GetMapping("/page")
    public String cocktailPage(Model model){
        List<CocktailEntity> allCocktail = cocktailBusiness.getAllCocktail();
        model.addAttribute("allCocktail", allCocktail);
        model.addAttribute("glasses", Glass.values());
        model.addAttribute("methods", Method.values());

        return "cocktail/index";
    }
    @PostMapping("/register")
    public String register(CockTailRequest request) throws IOException {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        cocktailBusiness.register(request,userName);
        return "redirect:/";
    }

    @GetMapping("/delete")
    @ResponseBody
    public ResponseEntity<Void> delete(@RequestParam String name, @RequestParam Long id){
        cocktailBusiness.deleteCocktail(id,name);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/Detail/{id}")
    public String Detail(@PathVariable Long id,Model model){
        CocktailEntity cocktail = cocktailBusiness.findCocktail(id);
        model.addAttribute("cocktail",cocktail);
        return "cocktail/cockTailDetail";
    }

    @PostMapping("/update")
    @ResponseBody
    public String update( CockTailUpdateRequest request) throws IOException {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        cocktailBusiness.updateCocktail(request,userName);

        return "redirect:/cocktail/index";
    }

}
