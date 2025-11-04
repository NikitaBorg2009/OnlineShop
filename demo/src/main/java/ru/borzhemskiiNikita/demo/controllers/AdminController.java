package ru.borzhemskiiNikita.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.borzhemskiiNikita.demo.models.Product;
import ru.borzhemskiiNikita.demo.services.AdminService;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    /**
     * Метод помогает изменить информацию старого продукта на новую
     *
     * @param oldProduct старый товар в виде {@link Product}
     * @param name       имя нового продукта
     * @param price      цена нового продукта
     * @param rank       рейтинг нового продукта
     * @param discount   скидка нового продукта
     * @param group      категория или группа нового продукта
     * @param count      кол-во нового продукта
     * @param id         код нового продукта
     * @return перекидывание на метод /accepted, если все проверки прошёл продукт, иначе перекидывание на метод /denied
     */
    @PostMapping("/changeProduct")
    public String changeProductShop(@ModelAttribute("product") Product oldProduct, @RequestParam("1name") String name,
                                    @RequestParam("1price") int price, @RequestParam("1rank") int rank,
                                    @RequestParam("1discount") int discount, @RequestParam("1group") String group,
                                    @RequestParam("1count") int count, @RequestParam("1id") int id) {

        if (adminService.changeProduct(oldProduct, name, price, rank, discount, group, count, id)) {
            return "redirect:/accepted";
        }
        return "redirect:/denied";
    }

    /**
     * Метод удаляет продукт из онлайн магазина
     *
     * @param product продукт который надо удалить в виде {@link Product}
     * @return перекидывание на метод /accepted, если продукт удалился, иначе перекидывание на метод /denied
     */
    @PostMapping("/deleteProduct")
    public String deleteProductSHOP(@ModelAttribute("product") Product product) {
        if (adminService.deleteProduct(product)) {
            return "redirect:/accepted";
        }
        return "redirect:/denied";
    }

    /**
     * Метод создаёт новый продукт или увеличивает кол-во продукта, который есть в магазине
     *
     * @param product продукт, который хотят создать или увеличить кол-во его
     * @return перекидывание на метод /accepted, если продукт создался, иначе перекидывание на метод /denied
     */
    @PostMapping("/createNewProduct")
    public String createNewProductShop(@ModelAttribute("product") Product product) {
        if (adminService.createNewProduct(product)) {
            return "redirect:/accepted";
        }
        return "redirect:/denied";
    }

    /**
     * Метод перекидывает администратора на страницу удаления товара из онлайн магазина
     *
     * @param model модель передаёт продукт, который надо удалить
     * @return представление страницы deletePage
     */
    @GetMapping("/getDeletePage")
    public String getDeletePage(Model model) {
        model.addAttribute("product", adminService.getProduct());
        return "deletePage";
    }

    /**
     * Метод перекидывает администратора на страницу изменения информации о товаре из онлайн магазина
     *
     * @param model модель передаёт продукт, который надо изменить
     * @return представление страницы changePage
     */
    @GetMapping("/getChangePage")
    public String getChangePage(Model model) {
        model.addAttribute("product", adminService.getProduct());
        return "changePage";
    }

    /**
     * Метод перекидывает администратора на страницу добавления продукта в онлайн магазин или увеличения его кол-ва
     *
     * @param model модель передаёт продукт, который надо добавить или увеличить его кол-во
     * @return представление страницы addProductSHOP
     */
    @GetMapping("/addProduct")
    public String getAddProductShop(Model model) {
        model.addAttribute("product", adminService.getProduct());
        return "addProductSHOP";
    }

    /**
     * Метод помогает сделать доставку бесплатной или платной (указать цену)
     *
     * @param choice выбор администратора (включить или выключить цену доставки)
     * @return перекидывание на метод /accepted
     */
    @PostMapping("/switchOnDelivery")
    public String switchOnDeliveryPrice(@RequestParam("choice") String choice) {
        adminService.switchOnDeliveryPrice(choice);
        return "redirect:/accepted";
    }

    /**
     * Метод перекидывает администратора на страницу включения или выключения доставки
     *
     * @param model модель передаёт информацию о доставки - включена или выключена она
     * @return представление страницы switchOnDeliveryPage
     */
    @GetMapping("/getSwitchOnDeliveryPage")
    public String switchOnDelivery(Model model) {

        if (adminService.switchOnDelivery()) {
            model.addAttribute("delivery", "Delivery is ON");
        } else {
            model.addAttribute("delivery", "Delivery is OFF");
        }

        return "switchOnDeliveryPage";
    }

    /**
     * Метод даёт возможность администратору изменить цену доставки
     *
     * @param money новая цена доставки
     * @return перекидывание на метод /accepted, если цена доставки изменилась и прошла проверки, иначе перекидывание
     * на метод /denied
     */
    @PostMapping("/changeDeliveryPrice")
    public String changeDP(@RequestParam("money") int money) {
        if (adminService.changeDeliveryPrice(money)) {
            return "redirect:/accepted";
        }
        return "redirect:/denied";
    }

    /**
     * Метод перекидывает администратора на страницу изменения цены доставки
     *
     * @return представление страницы changeDeliveryPricePage
     */
    @GetMapping("/getChangeDeliveryPricePage")
    public String getCPP() {
        return "changeDeliveryPricePage";
    }

    /**
     * Метод перекидывает администратора на меню админа
     *
     * @return представление страницы adminConsole
     */
    @GetMapping("/adminConsole")
    public String getAC() {
        return "adminConsole";
    }
}
