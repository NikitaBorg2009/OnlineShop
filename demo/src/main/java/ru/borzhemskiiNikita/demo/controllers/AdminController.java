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

    @PostMapping("/changeProduct")
    public String changeProductShop(@ModelAttribute("product") Product oldProduct, @RequestParam("1name") String name,
                                    @RequestParam("1price") int price, @RequestParam("1rank") int rank,
                                    @RequestParam("1discount") int discount, @RequestParam("1group") String group,
                                    @RequestParam("1count") int count, @RequestParam("1id") int id) {

        if (adminService.changeProduct(oldProduct, name, price, rank, discount, group, count, id)) {
            return "redirect:/accepted";
        }
        else {
            return "redirect:/denied";
        }
    }

    @PostMapping("/deleteProduct")
    public String deleteProductSHOP(@ModelAttribute("product") Product product) {
        if (adminService.deleteProduct(product)) {
            return "redirect:/accepted";
        }
        else {
            return "redirect:/denied";
        }
    }

    @PostMapping("/createNewProduct")
    public String createNewProductShop(@ModelAttribute("product") Product product) {
        if (adminService.createNewProduct(product)) {
            return "redirect:/accepted";
        }
        else {
            return "redirect:/denied";
        }
    }

    @GetMapping("/getDeletePage")
    public String getDeletePage(Model model) {
        model.addAttribute("product", adminService.getProduct());
        return "deletePage";
    }

    @GetMapping("/getChangePage")
    public String getChangePage(Model model) {
        model.addAttribute("product", adminService.getProduct());
        return "changePage";
    }

    @GetMapping("/addProduct")
    public String getAddProductShop(Model model) {
        model.addAttribute("product", adminService.getProduct());
        return "addProductSHOP";
    }

    @PostMapping("/switchOnDelivery")
    public String switchOnDeliveryPrice(@RequestParam("choice") String choice) {
        adminService.switchOnDeliveryPrice(choice);
        return "redirect:/accepted";
    }

    @GetMapping("/getSwitchOnDeliveryPage")
    public String switchOnDelivery(Model model) {

        if (adminService.switchOnDelivery()) {
            model.addAttribute("delivery", "Delivery is ON");
        }
        else {
            model.addAttribute("delivery", "Delivery is OFF");
        }

        return "switchOnDeliveryPage";
    }

    @PostMapping("/changeDeliveryPrice")
    public String changeDP(@RequestParam("money") int money) {
        if (adminService.changeDeliveryPrice(money)) {
            return "redirect:/accepted";
        }
        else {
            return "redirect:/denied";
        }
    }

    @GetMapping("/getChangeDeliveryPricePage")
    public String getCPP() {
        return "changeDeliveryPricePage";
    }

    @GetMapping("/adminConsole")
    public String getAC() {
        return "adminConsole";
    }
}
