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

    @PostMapping("/addProductBASKET")
    public String addProductBasket(@RequestParam("id") int id, @RequestParam("count") int count) {
        if (!category.checkEqualCountOfProducts(id, count)) {
            return "redirect:/denied";
        }

        category.addProductToTheBasket(basket, id, count);

        return "redirect:/nice";
    }

    @GetMapping("/getAddProductPage")
    public String addProductPage() {
        return "addProductPage";
    }

    @GetMapping("/getBasketPageTable")
    public String getBasketTable(Model model) {
        model.addAttribute("basket", basket);
        return "basketPageTable";
    }

    @GetMapping("/getBasket")
    public String getUsersBasket() {
        return "redirect:/getBasketPageTable";
    }

    @PostMapping("/deleteProductSHOP")
    public String deleteProductInTheShop(@RequestParam("id") int id, @RequestParam("count") int count) {
        if (!category.checkEqualCountOfProducts(id, count)) {
            return "redirect:/denied";
        }

        category.deleteProductToTheBasket(basket, id, count);

        return "redirect:/nice";
    }

    @GetMapping("/getDeleteProduct")
    public String getDelPage() {
        return "deleteProductPage";
    }

    @PostMapping("/orderDelivery")
    public String orderDelivery(@RequestParam("choice") String choice) {
        if (choice.equals("yes")) {
            if (users.payDelivery(category.getDeliveryPrice())) {
                return "redirect:/nice";
            }
            return "redirect:/denied";
        }
        else if (choice.equals("no")) {
            return "redirect:/nice";
        }

        return "redirect:/denied";
    }

    @GetMapping("/getDeliveryPage")
    public String getDelivery() {
        return "deliveryPage";
    }

    @PostMapping("/payForProductsBalance")
    public String payForProducts(Model model) {
        int money1 = 0;

        for (Product p1 : basket.getBasket()) {
            money1 += p1.getPrice()*p1.getCount();
        }

        if (users.buyBasketWithMoney(money1)) {
            basket.getBasket().clear();

            if (category.isDelivery()) {
                return "redirect:/getDeliveryPage";
            }
            return "redirect:/nice";
        }

        return "redirect:/denied";
    }

    @GetMapping("/getPayPage")
    public String getPayProducts(Model model) {
        int money = 0;

        for (Product p1 : basket.getBasket()) {
            money += p1.getPrice();
        }

        model.addAttribute("money", money);
        return "payBasket";
    }

    @GetMapping("/userConsole")
    public String getUserMenu() {
        return "userMenu";
    }
}
