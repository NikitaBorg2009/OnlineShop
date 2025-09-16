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
import java.util.stream.Stream;

@Controller
public class FilterController {
    @Autowired
    private Category category;
    private List<Product> products = new ArrayList<>();

    @PostMapping("/priceFilterChange")
    public String priceFilter(@RequestParam("minPrice") int minPrice, @RequestParam("maxPrice") int maxPrice,
                              Model model) {
        List<Product> products2 = products.stream().filter(product ->
                product.getPrice()>=minPrice && product.getPrice()<=maxPrice).toList();

        model.addAttribute("category", products2);
        return "categoryFilterPage";
    }

    @PostMapping("/rankFilterChange")
    public String rankFilter(@RequestParam("minRank") int minRank, @RequestParam("maxRank") int maxRank, Model model) {
        List<Product> products2 = products.stream().filter(product ->
                product.getRank()>=minRank && product.getRank()<=maxRank).toList();

        model.addAttribute("category", products2);
        return "categoryFilterPage";
    }

    @PostMapping("/nameFilterChange")
    public String rankFilter(@RequestParam("name") String name, Model model) {
        Stream<Product> nameFilter = products.stream().filter((product) -> {
            String help = "";

            for (int i = 0; i < name.length(); i++) {
                help += product.getName().charAt(i);
            }

            if (help.equals(name)) {
                return true;
            }
            return false;
        });

        List<Product> products2 = nameFilter.toList();

        model.addAttribute("category", products2);
        return "categoryFilterPage";
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
        products = new ArrayList<>();
        products.addAll(category.getProducts());
        return "filterConsole";
    }
}
