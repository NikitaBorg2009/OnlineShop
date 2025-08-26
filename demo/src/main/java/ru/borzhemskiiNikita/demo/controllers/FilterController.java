package ru.borzhemskiiNikita.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.borzhemskiiNikita.demo.models.Category;
import ru.borzhemskiiNikita.demo.models.Product;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FilterController {
    @Autowired
    private Category category;
    private List<Product> products = new ArrayList<>();

    @PostMapping("/priceFilterChange")
    public String priceFilter(@RequestParam("minPrice") int minPrice, @RequestParam("maxPrice") int maxPrice) {
        List<Product> products2 = new ArrayList<>();

        for (Product p : products) {
            if (p.getPrice() >= minPrice && p.getPrice() <= maxPrice) {
                products2.add(p);
            }
        }

        products = products2;

        return "redirect:/nice";
    }

    @PostMapping("/rankFilterChange")
    public String rankFilter(@RequestParam("minRank") int minRank, @RequestParam("maxRank") int maxRank) {
        List<Product> products2 = new ArrayList<>();

        for (Product p : products) {
            if (p.getRank() >= minRank && p.getRank() <= maxRank) {
                products2.add(p);
            }
        }

        products = products2;

        return "redirect:/nice";
    }

    @PostMapping("/nameFilterChange")
    public String rankFilter(@RequestParam("name") String name) {
        List<Product> products2 = new ArrayList<>();

        for (Product p : products) {
            String help = "";

            for (int i = 0; i < name.length(); i++) {
                help += p.getName().charAt(i);
            }

            if (help.equals(name)) {
                products2.add(p);
            }
        }

        products = products2;

        return "redirect:/nice";
    }

    @GetMapping("/getCategoryFilterPage")
    public String getCategoryFilter(Model model) {
        model.addAttribute("category", products);
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
        products.addAll(category.getProducts());
        return "filterConsole";
    }
}
