package ru.borzhemskiiNikita.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.borzhemskiiNikita.demo.services.UserService;

@Controller
public class UsersController {
    @Autowired
    private UserService userService;

    @GetMapping("/denied")
    public String accessDenied() {
        return "accessDeniedPage";
    }

    @GetMapping("/accepted")
    public String getNice() {
        return "acceptedPage";
    }

    @GetMapping("/access")
    public String getAccess() {
        return "goodJobPage";
    }

    @GetMapping("/productsInTheShop")
    public String getPITS(Model model) {
        model.addAttribute("category", userService.getCategory());
        return "productsInTheShop";
    }

    @PostMapping("/changeLogin")
    public String changeLogin(@RequestParam("login") String login) {
        userService.changeLogin(login);
        return "redirect:/access";
    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestParam("password") String password) {
        userService.changePassword(password);
        return "redirect:/access";
    }

    @GetMapping("/changeUserInfo")
    public String changeUI() {
        return "changeUserInfo";
    }

    @PostMapping("/addBalance")
    public String addBalance(@RequestParam("money") int money) {
        userService.addBalance(money);
        return "redirect:/accepted";
    }

    @GetMapping("/topBankCard")
    public String putMoney(Model model) {
        if (userService.checkUsersPocketCount()) {
            return "redirect:/denied";
        }

        model.addAttribute("pocket", userService.getUsersPocket());
        return "putMoney";
    }

    @PostMapping("/addProductBASKET")
    public String addProductBasket(@RequestParam("id") int id, @RequestParam("count") int count) {
        if (userService.checkEqualCountOfProductsCategory(id, count)) {
            return "redirect:/denied";
        }

        userService.addProductBasket(id, count);

        return "redirect:/accepted";
    }

    @GetMapping("/getAddProductPage")
    public String addProductPage() {
        return "addProductPage";
    }

    @GetMapping("/getBasketPageTable")
    public String getBasketTable(Model model) {
        model.addAttribute("basket", userService.getBasket());
        return "basketPageTable";
    }

    @GetMapping("/getBasket")
    public String getUsersBasket() {
        return "redirect:/getBasketPageTable";
    }

    @PostMapping("/deleteProductSHOP")
    public String deleteProductInTheShop(@RequestParam("id") int id, @RequestParam("count") int count) {
        if (userService.checkEqualCountOfProductsCategory(id, count)) {
            return "redirect:/denied";
        }

        userService.deleteProductInTheShop(id, count);

        return "redirect:/accepted";
    }

    @GetMapping("/getDeleteProduct")
    public String getDelPage() {
        return "deleteProductPage";
    }

    @PostMapping("/orderDelivery")
    public String orderDelivery(@RequestParam("choice") String choice) {
        if (userService.checkChoice(choice) && userService.checkPayDelivery()) {
            return "redirect:/accepted";
        }
        else if (!userService.checkChoice(choice)) {
            return "redirect:/accepted";
        }

        return "redirect:/denied";
    }

    @GetMapping("/getDeliveryPage")
    public String getDelivery() {
        return "deliveryPage";
    }

    @PostMapping("/payForProductsBalance")
    public String payForProducts(Model model) {
        if (userService.checkBuyBasketWithMoney()) {
            userService.clearBasket();

            if (userService.isDeliveryCategory()) {
                return "redirect:/getDeliveryPage";
            }
            return "redirect:/accepted";
        }

        return "redirect:/denied";
    }

    @GetMapping("/getPayPage")
    public String getPayProducts(Model model) {
        model.addAttribute("money", userService.changeMoneyUser());
        return "payBasket";
    }

    @GetMapping("/userConsole")
    public String getUserMenu() {
        return "userMenu";
    }
}
