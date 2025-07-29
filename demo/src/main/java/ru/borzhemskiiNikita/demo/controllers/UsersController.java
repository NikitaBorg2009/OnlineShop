package ru.borzhemskiiNikita.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.borzhemskiiNikita.demo.models.*;

@Controller
public class UsersController {
    @Autowired
    private Category category;
    @Autowired
    private Basket basket;
    @Autowired
    private UsersArray users;

    @GetMapping("/denied")
    public String accessDenied() {
        return "accessDeniedPage";
    }

    @GetMapping("/nice")
    public String getNice() {
        return "nicePage";
    }

    @GetMapping("/access")
    public String getAccess() {
        return "goodJobPage";
    }

    @GetMapping("/productsInTheShop")
    public String getPITS(Model model) {
        model.addAttribute("category", category);
        return "productsInTheShop";
    }

    @PostMapping("/changeLogin")
    public String changeLogin(@RequestParam("login") String login) {
        User user = new User();
        user.setLogin(login);

        users.changeUserLogin(user);

        return "redirect:/access";
    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestParam("password") String password) {
        User user = new User();
        user.setPassword(password);

        users.changeUserPassword(user);

        return "redirect:/access";
    }

    @GetMapping("/changeUserInfo")
    public String changeUI() {
        return "changeUserInfo";
    }

    @PostMapping("/addBalance")
    public String addBalance(@RequestParam("money") int money) {
        users.addMoneyUser(money);
        return "redirect:/nice";
    }

    @GetMapping("/topBankCard")
    public String putMoney(Model model) {
        if (users.getUserPocket() == -1) {
            return "redirect:/denied";
        }

        model.addAttribute("pocket", users.getUserPocket());
        return "putMoney";
    }

    @GetMapping("/userConsole")
    public String getUserMenu() {
        return "userMenu";
    }
}