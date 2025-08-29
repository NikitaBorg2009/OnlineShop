package ru.borzhemskiiNikita.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TableController {
    @GetMapping("/getFindPage")
    public String getFindPage(Model model) {
        return "redirect:/productsInTheShop";
    }
}
