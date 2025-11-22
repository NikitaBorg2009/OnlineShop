package ru.borzhemskiiNikita.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.borzhemskiiNikita.demo.services.UserService;

/**
 * Класс, реализующий запросы связанные с функционалом покупателя онлайн магазина
 */
@Controller
public class UsersController {
    @Autowired
    private UserService userService;

    /**
     * Метод перенаправляет пользователя на страницу с отказом, невозможно выполнить действие
     *
     * @return представление страницы accessDeniedPage
     */
    @GetMapping("/denied")
    public String accessDenied() {
        return "accessDeniedPage";
    }

    /**
     * Метод перенаправляет пользователя на страницу с одобрением, аккаунт авторизирован
     *
     * @return представление страницы acceptedPage
     */
    @GetMapping("/accepted")
    public String getNice() {
        return "acceptedPage";
    }

    /**
     * Метод перенаправляет пользователя на страницу с успешным окончанием действий или покупки корзины
     *
     * @return представление страницы goodJobPage
     */
    @GetMapping("/access")
    public String getAccess() {
        return "goodJobPage";
    }

    /**
     * Метод перенаправляет пользователя на страницу с товарами в магазине
     *
     * @param model модель передаёт список товаров
     * @return представление страницы productsInTheShop
     */
    @GetMapping("/productsInTheShop")
    public String getPITS(Model model) {
        model.addAttribute("category", userService.getCategory());
        return "productsInTheShop";
    }

    /**
     * Метод изменяет логин пользователя и возвращает страницу с успешным изменением
     *
     * @param login логин пользователя новый
     * @return перенаправляет на метод одобрения
     */
    @PostMapping("/changeLogin")
    public String changeLogin(@RequestParam("login") String login) {
        userService.changeLogin(login);
        return "redirect:/access";
    }

    /**
     * Метод изменяет пароль пользователя и возвращает страницу с успешным изменением
     *
     * @param password новый пароль пользователя
     * @return перенаправляет на метод /access
     */
    @PostMapping("/changePassword")
    public String changePassword(@RequestParam("password") String password) {
        userService.changePassword(password);
        return "redirect:/access";
    }

    /**
     * Метод изменяет информацию о пользователе
     *
     * @return представление страницы changeUserInfo
     */
    @GetMapping("/changeUserInfo")
    public String changeUI() {
        return "changeUserInfo";
    }

    /**
     * Метод увеличивает баланс пользователя на нужную сумму
     *
     * @param money кол-во денег на которую нужно увеличить баланс кошелька
     * @return перенаправляет на метод /accepted c одобрением
     */
    @PostMapping("/addBalance")
    public String addBalance(@RequestParam("money") int money) {
        userService.addBalance(money);
        return "redirect:/accepted";
    }

    /**
     * Метод пополняет баланс кошелька пользователя
     *
     * @param model модель передаёт новый баланс кошелька на страницу
     * @return перенаправляет на метод /denied, если пользователь ввёл отрицательное число или на представление
     * страницы putMoney
     */
    @GetMapping("/topBankCard")
    public String putMoney(Model model) {
        if (userService.checkUsersPocketCount()) {
            return "redirect:/denied";
        }

        model.addAttribute("pocket", userService.getUsersPocket());
        return "putMoney";
    }

    /**
     * Метод добавляет товар в корзину пользователя
     *
     * @param id    код товара
     * @param count кол-во товаров
     * @return перенаправляет на метод /denied, если нет такого товара в магазине или кол-во введённого товара
     * превышает, чем в магазине. Если все проверки прошёл продукт, то перенаправляет на метод /accepted
     */
    @PostMapping("/addProductBASKET")
    public String addProductBasket(@RequestParam("id") int id, @RequestParam("count") int count) {
        if (!userService.checkEqualCountOfProductsCategory(id, count)) {
            return "redirect:/denied";
        }

        userService.addProductBasket(id, count);

        return "redirect:/accepted";
    }

    /**
     * Метод перенаправляет пользователя на страницу добавления продукта в корзину пользователя
     *
     * @return представление страницы addProductPage
     */
    @GetMapping("/getAddProductPage")
    public String addProductPage() {
        return "addProductPage";
    }

    /**
     * Метод перенаправляет на страницу товаров, которые пользователь добавил в корзину
     *
     * @param model модель передаёт новую корзину на страницу
     * @return представление страницы basketPageTable
     */
    @GetMapping("/getBasketPageTable")
    public String getBasketTable(Model model) {
        model.addAttribute("basket", userService.getBasket());
        return "basketPageTable";
    }

    /**
     * Метод перенаправляет на метод, показывающий таблицу товаров в корзине
     *
     * @return перенаправляет на метод /getBasketPageTable
     */
    @GetMapping("/getBasket")
    public String getUsersBasket() {
        return "redirect:/getBasketPageTable";
    }

    /**
     * Метод удаляет товар из корзины пользователя
     *
     * @param id    код товара
     * @param count кол-во товаров
     * @return перенаправляет на метод /denied, если нет такого товара в магазине.
     * Если все проверки прошёл продукт, то перенаправляет на метод /accepted
     */
    @PostMapping("/deleteProductSHOP")
    public String deleteProductInTheShop(@RequestParam("id") int id, @RequestParam("count") int count) {
        if (!userService.checkEqualBasket(id, count)) {
            return "redirect:/denied";
        }

        userService.deleteProductInTheShop(id, count);

        return "redirect:/accepted";
    }

    /**
     * Метод перенаправляет пользователя на страницу удаления продукта из корзины
     *
     * @return представление страницы deleteProductPage
     */
    @GetMapping("/getDeleteProduct")
    public String getDelPage() {
        return "deleteProductPage";
    }

    /**
     * Метод оформляет пользователю доставку продуктов из онлайн магазина
     *
     * @param choice выбор пользователя (нужна доставку ему или нет)
     * @return перенаправляет на метод /denied, если не хватает средств на карте, чтобы оплатить доставку или если все
     * проверки прошёл, то перенаправляет на метод /accepted
     */
    @PostMapping("/orderDelivery")
    public String orderDelivery(@RequestParam("choice") String choice) {
        if (userService.checkChoice(choice) && userService.checkPayDelivery()) {
            return "redirect:/accepted";
        } else if (!userService.checkChoice(choice)) {
            return "redirect:/accepted";
        }

        return "redirect:/denied";
    }

    /**
     * Метод перенаправляет пользователя на страницу оформления доставки
     *
     * @return перенаправляет страницы deliveryPage
     */
    @GetMapping("/getDeliveryPage")
    public String getDelivery() {
        return "deliveryPage";
    }

    /**
     * Метод оплачивает пользователю корзину товаров
     *
     * @return При успешной покупки корзины - перенаправляет на метод /getDeliveryPage или /accepted в зависимости от
     * выбора пользователя. Если не хватает средств, то перенаправляет на метод /denied
     */
    @PostMapping("/payForProductsBalance")
    public String payForProducts() {
        if (userService.checkBuyBasketWithMoney()) {
            userService.clearBasket();

            if (userService.isDeliveryCategory()) {
                return "redirect:/getDeliveryPage";
            }
            return "redirect:/accepted";
        }

        return "redirect:/denied";
    }

    /**
     * Метод перенаправляет пользователя на страницу оплаты корзины
     *
     * @param model модель передаёт корзину товаров на страницу
     * @return представление страницы payBasket
     */
    @GetMapping("/getPayPage")
    public String getPayProducts(Model model) {
        model.addAttribute("money", userService.changeMoneyUser());
        return "payBasket";
    }

    /**
     * Метод перенаправляет пользователя на меню покупателя
     *
     * @return представление страницы userMenu
     */
    @GetMapping("/userConsole")
    public String getUserMenu() {
        return "userMenu";
    }
}
