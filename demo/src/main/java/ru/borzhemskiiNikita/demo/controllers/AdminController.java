package ru.borzhemskiiNikita.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.borzhemskiiNikita.demo.models.Category;
import ru.borzhemskiiNikita.demo.models.Product;

@Controller
public class AdminController {
    @Autowired
    private Category category;
    @Autowired
    private Product product1;



    @PostMapping("/changeProduct")
    public String changeProductShop(@ModelAttribute("product") Product oldProduct, @RequestParam("1name") String name,
                                    @RequestParam("1price") int price, @RequestParam("1rank") int rank,
                                    @RequestParam("1discount") int discount, @RequestParam("1group") String group,
                                    @RequestParam("1count") int count, @RequestParam("1id") int id) {

        Product newProduct = new Product();

        newProduct.setName(name);
        newProduct.setPrice(price);
        newProduct.setRank(rank);
        newProduct.setDiscount(discount);
        newProduct.setGroup(group);
        newProduct.setCount(count);
        newProduct.setId(id);

        if (category.thereIsTheProduct(oldProduct)) {
            category.changeProduct(oldProduct, newProduct);
            return "redirect:/nice";
        }

        return "redirect:/denied";
    }

    @PostMapping("/deleteProduct")
    public String deleteProductSHOP(@ModelAttribute("product") Product product) {
        if (category.thereIsTheProduct(product)) {
            category.deleteProduct(product);
            return "redirect:/nice";
        }
        return "redirect:/denied";
    }

    @PostMapping("/createNewProduct")
    public String createNewProductShop(@ModelAttribute("product") Product product) {
        if (category.thereIsTheProduct(product)) {
            category.riseCountProduct(product);
            return "redirect:/nice";
        }

        category.getProducts().add(product);
        return "redirect:/nice";
    }

    @GetMapping("/getDeletePage")
    public String getDeletePage(Model model) {
        model.addAttribute("product", product1);
        return "deletePage";
    }

    @GetMapping("/getChangePage")
    public String getChangePage(Model model) {
        model.addAttribute("product", product1);
        return "changePage";
    }

    @GetMapping("/addProduct")
    public String getAddProductShop(Model model) {
        model.addAttribute("product", product1);
        return "addProductSHOP";
    }

    @GetMapping("/adminConsole")
    public String getAC() {
        return "adminConsole";
    }
}