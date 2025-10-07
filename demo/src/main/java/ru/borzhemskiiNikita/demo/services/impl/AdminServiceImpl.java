package ru.borzhemskiiNikita.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.borzhemskiiNikita.demo.models.Category;
import ru.borzhemskiiNikita.demo.models.Product;
import ru.borzhemskiiNikita.demo.services.AdminService;

public class AdminServiceImpl implements AdminService {
    @Autowired
    private Category category;
    @Autowired
    private Product product;

    @Override
    public Product getProduct() {
        return product;
    }

    @Override
    public boolean switchOnDelivery() {
        return category.isDelivery();
    }

    @Override
    public boolean changeDeliveryPrice(int money) {
        if (money < 0) {
            return false;
        }
        else if (!category.isDelivery()) {
            return false;
        }

        category.setDeliveryPrice(money);
        return true;
    }

    @Override
    public void switchOnDeliveryPrice(String choice) {
        if (choice.equals("yes")) {
            category.changeDeliveryOnOff();
        }
    }

    @Override
    public boolean createNewProduct(Product product) {
        if (product.getCount() <= 0 || product.getPrice() <= 0 || product.getRank() <= 0 || product.getDiscount() < 0) {
            return false;
        }

        if (category.thereIsTheProduct(product)) {
            category.riseCountProduct(product);
            return true;
        }

        category.getProducts().add(product);

        return true;
    }

    @Override
    public boolean deleteProduct(Product product) {
        if (product.getCount() <= 0 || product.getPrice() <= 0 || product.getRank() <= 0 || product.getDiscount() < 0) {
            return false;
        }

        if (category.thereIsTheProduct(product)) {
            category.deleteProduct(product);
            return true;
        }

        return false;
    }

    @Override
    public boolean changeProduct(Product oldProduct, String name, int price, int rank, int discount, String group,
                                    int count, int id) {
        Product newProduct = new Product();

        newProduct.setName(name);
        newProduct.setPrice(price);
        newProduct.setRank(rank);
        newProduct.setDiscount(discount);
        newProduct.setGroup(group);
        newProduct.setCount(count);
        newProduct.setId(id);

        if (newProduct.getCount() <= 0 || newProduct.getPrice() <= 0 || newProduct.getRank() <= 0 ||
                newProduct.getDiscount() < 0) {
            return false;
        }

        if (category.thereIsTheProduct(oldProduct)) {
            category.changeProduct(oldProduct, newProduct);
            return true;
        }

        return false;
    }
}
