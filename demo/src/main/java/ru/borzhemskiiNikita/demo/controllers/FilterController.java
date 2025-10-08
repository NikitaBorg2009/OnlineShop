package ru.borzhemskiiNikita.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.borzhemskiiNikita.demo.models.Product;
import ru.borzhemskiiNikita.demo.services.FilterService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Controller
public class FilterController {
    @Autowired
    private FilterService filterService;

    @PostMapping("/priceFilterChange")
    public String priceFilter(@RequestParam("minPrice") int minPrice, @RequestParam("maxPrice") int maxPrice,
                              Model model) {
        model.addAttribute("category", filterService.priceFilter(minPrice, maxPrice));
        return "categoryFilterPage";
    }

    @PostMapping("/rankFilterChange")
    public String rankFilter(@RequestParam("minRank") int minRank, @RequestParam("maxRank") int maxRank, Model model) {
        model.addAttribute("category", filterService.rankFilter(minRank, maxRank));
        return "categoryFilterPage";
    }

    @PostMapping("/nameFilterChange")
    public String nameFilter(@RequestParam("name") String name, Model model) {
        model.addAttribute("category", filterService.nameFilter(name));
        return "categoryFilterPage";
    }

    @GetMapping("/getCategoryFilterPage")
    public String getCategoryFilter(Model model) {
        model.addAttribute("category", filterService.getProducts());
        return "categoryFilterPage";
    }

    @GetMapping("/getRankFilterPage")
    public String getRankFilter() {
        return "rankFilter";
    }

    @GetMapping("/getPriceFilterPage")
    public String getPriceFilter() {
        return "priceFilter";
    }

    @GetMapping("/getNameFilterPage")
    public String getNameFilter() {
        return "findProductByNameFilter";
    }

    @GetMapping("/getFilterConsolePage")
    public String getFilterConsole() {
        filterService.filterConsoleUpdate();
        return "filterConsole";
    }
}
