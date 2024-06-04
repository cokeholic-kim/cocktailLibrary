package org.cocktail.admin.domain.cocktail.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cocktail.admin.domain.cocktail.business.CocktailBusiness;
import org.cocktail.admin.domain.cocktail.controller.model.CockTailRequest;
import org.cocktail.admin.domain.cocktail.controller.model.CockTailUpdateRequest;
import org.cocktail.admin.domain.cocktail.controller.model.CocktailRequests;
import org.cocktail.db.cocktail.CocktailEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PostMapping("/register")
    public String register(CockTailRequest request){
        cocktailBusiness.register(request);
        return "redirect:/";
    }

    @PostMapping("/registerAll")
    @ResponseBody
    public  ResponseEntity<Void> registerAll(CocktailRequests requests){
        System.out.println("CocktailController.registerAll");
        cocktailBusiness.registerAll(requests);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<Void> delete(@PathVariable Long id){
//        cocktailBusiness.deleteCocktail(id);
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
    public ResponseEntity<Void> update( CockTailUpdateRequest request){
        System.out.println("request = " + request);

        cocktailBusiness.updateCocktail(request);

        return ResponseEntity.noContent().build();
    }

}
