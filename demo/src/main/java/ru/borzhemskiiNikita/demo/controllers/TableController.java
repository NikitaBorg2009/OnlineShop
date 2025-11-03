package ru.borzhemskiiNikita.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TableController {

    /**
     * Метод перекидывает пользователя на страницу с таблицей товаров в онлайн магазине
     *
     * @return представление страницы productsInTheShop
     */
    @GetMapping("/getFindPage")
    public String getFindPage() {
        return "redirect:/productsInTheShop";
    }
}
