package org.cocktail.admin.domain.banner.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cocktail.admin.domain.Ingredient.controller.model.IngredientResponse;
import org.cocktail.admin.domain.banner.business.BannerBusiness;
import org.cocktail.admin.domain.banner.controller.model.BannerRequest;
import org.cocktail.admin.domain.banner.controller.model.BannerResponse;
import org.cocktail.admin.domain.cocktail.controller.model.CocktailResponse;
import org.cocktail.db.CocktailIngredient.enums.Unit;
import org.cocktail.db.banner.BannerRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("banner")
@RequiredArgsConstructor
public class BannerController {
    private final BannerBusiness bannerBusiness;

    @GetMapping("page")
    public String bannerPage(Model model) {
        List<BannerResponse> allBanner = bannerBusiness.getAllBanner();
        model.addAttribute("allBanner", allBanner);
        model.addAttribute("pagename","banner");

        return "banner/index";
    }

    @PostMapping("register")
    public String register(@ModelAttribute BannerRequest bannerRequest){
        //TODO : 파일저장 배너저장.
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        bannerBusiness.saveBanner(bannerRequest,userName);
        return "redirect:/banner/page";
    }

    @GetMapping("delete")
    public String delete(@RequestParam String name){
        bannerBusiness.deleteBanner(name);
        return "redirect:/banner/index";
    }

    @GetMapping("/detail/{name}")
    public String detail(@PathVariable String name, Model model) {
        BannerResponse banner = bannerBusiness.findBanner(name);
        model.addAttribute("banner",banner);

        return "banner/bannerDetail";
    }

    @PostMapping("update")
    public String update(BannerRequest bannerRequest){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        bannerBusiness.updateBanner(bannerRequest,userName);

        return "redirect:/banner/index";
    }

    

}
