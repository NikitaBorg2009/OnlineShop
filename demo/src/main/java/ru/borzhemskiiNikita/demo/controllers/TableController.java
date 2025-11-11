package ru.borzhemskiiNikita.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Класс, реализующий запросы связанные с общей таблицей товаров онлайн магазина
 */
@Controller
public class TableController {

    /**
     * Метод перенаправляет пользователя на страницу с таблицей товаров
     *
     * @return представление страницы productsInTheShop
     */
    @GetMapping("/getFindPage")
    public String getFindPage() {
        return "redirect:/productsInTheShop";
    }
}
