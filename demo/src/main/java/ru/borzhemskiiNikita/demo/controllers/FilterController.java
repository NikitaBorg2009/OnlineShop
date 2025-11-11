package ru.borzhemskiiNikita.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.borzhemskiiNikita.demo.services.FilterService;

/**
 * Класс, реализующий запросы связанные с фильтрацией продуктов из онлайн магазина
 */
@Controller
public class FilterController {
    @Autowired
    private FilterService filterService;

    /**
     * Метод фильтрует список товаров по цене
     *
     * @param minPrice минимальная цена товара
     * @param maxPrice максимальная цена товара
     * @param model    модель передаёт фильтрованный список товаров на страницу
     * @return представление страницы categoryFilterPage
     */
    @PostMapping("/priceFilterChange")
    public String priceFilter(@RequestParam("minPrice") int minPrice, @RequestParam("maxPrice") int maxPrice,
                              Model model) {
        model.addAttribute("category", filterService.priceFilter(minPrice, maxPrice));
        return "categoryFilterPage";
    }

    /**
     * Метод фильтрует список товаров по рейтингу
     *
     * @param minRank минимальный рейтинг товара
     * @param maxRank максимальный рейтинг товара
     * @param model   модель передаёт фильтрованный список товаров на страницу
     * @return представление страницы categoryFilterPage
     */
    @PostMapping("/rankFilterChange")
    public String rankFilter(@RequestParam("minRank") int minRank, @RequestParam("maxRank") int maxRank, Model model) {
        model.addAttribute("category", filterService.rankFilter(minRank, maxRank));
        return "categoryFilterPage";
    }

    /**
     * Метод фильтрует список товаров по имени или начальных букв
     *
     * @param name  имя или начальные буквы товара
     * @param model модель передаёт фильтрованный список товаров на страницу
     * @return представление страницы categoryFilterPage
     */
    @PostMapping("/nameFilterChange")
    public String nameFilter(@RequestParam("name") String name, Model model) {
        model.addAttribute("category", filterService.nameFilter(name));
        return "categoryFilterPage";
    }

    /**
     * Метод перенаправляет пользователя на страницу списка товаров с фильтрами
     *
     * @param model модель передаёт фильтрованный список товаров на страницу
     * @return представление страницы categoryFilterPage
     */
    @GetMapping("/getCategoryFilterPage")
    public String getCategoryFilter(Model model) {
        model.addAttribute("category", filterService.getProducts());
        return "categoryFilterPage";
    }

    /**
     * Метод перенаправляет пользователя на страницу с фильтром по рейтингу
     *
     * @return представление страницы rankFilter
     */
    @GetMapping("/getRankFilterPage")
    public String getRankFilter() {
        return "rankFilter";
    }

    /**
     * Метод перенаправляет пользователя на страницу с фильтром по цене
     *
     * @return представление страницы priceFilter
     */
    @GetMapping("/getPriceFilterPage")
    public String getPriceFilter() {
        return "priceFilter";
    }

    /**
     * Метод перенаправляет пользователя на страницу с фильтром по имени
     *
     * @return представление страницы findProductByNameFilter
     */
    @GetMapping("/getNameFilterPage")
    public String getNameFilter() {
        return "findProductByNameFilter";
    }

    /**
     * Метод перенаправляет пользователя на страницу меню всех фильтров
     *
     * @return представление страницы filterConsole
     */
    @GetMapping("/getFilterConsolePage")
    public String getFilterConsole() {
        filterService.filterConsoleUpdate();
        return "filterConsole";
    }
}
