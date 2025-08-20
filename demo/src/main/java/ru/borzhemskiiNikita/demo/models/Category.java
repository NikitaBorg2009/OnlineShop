package ru.borzhemskiiNikita.demo.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Component
public class Category {
    private List<Product> products = new ArrayList<>();
    private boolean delivery;
    private int deliveryPrice;

    public boolean thereIsTheProduct(Product p) {
        for (Product value : products) {
            if (value.getName().equals(p.getName()) && value.getPrice() == p.getPrice() &&
                    value.getRank() == p.getRank() && value.getDiscount() == p.getDiscount() &&
                    value.getGroup().equals(p.getGroup()) &&
                    value.getId() == p.getId()) {
                return true;
            }
        }

        return false;
    }

    public void changeProduct(Product p, Product product2) {
        for (Product value : products) {
            if (value.getName().equals(p.getName()) && value.getPrice() == p.getPrice() &&
                    value.getRank() == p.getRank() && value.getDiscount() == p.getDiscount() &&
                    value.getGroup().equals(p.getGroup()) && value.getCount() == p.getCount() &&
                    value.getId() == p.getId()) {

                value.setName(product2.getName());
                value.setPrice(product2.getPrice());
                value.setRank(product2.getRank());
                value.setDiscount(product2.getDiscount());
                value.setGroup(product2.getGroup());
                value.setCount(product2.getCount());
                value.setId(product2.getId());
            }
        }
    }

    public void deleteProduct(Product p) {
        products.removeIf(value -> value.getName().equals(p.getName()) && value.getPrice() == p.getPrice() &&
                value.getRank() == p.getRank() && value.getDiscount() == p.getDiscount() &&
                value.getGroup().equals(p.getGroup()) &&
                value.getId() == p.getId());
    }

    public void riseCountProduct(Product p) {
        for (Product value : products) {
            if (value.getName().equals(p.getName()) && value.getPrice() == p.getPrice() &&
                    value.getRank() == p.getRank() && value.getDiscount() == p.getDiscount() &&
                    value.getGroup().equals(p.getGroup()) && value.getCount() == p.getCount() &&
                    value.getId() == p.getId()) {

                value.riseCount(p.getCount());
            }
        }
    }

    public void addProductToTheBasket(Basket basket, int id, int count) {
        boolean check = false;
        boolean check2 = false;

        for (Product value : products) {
            if (value.getId() == id && (value.getCount() - count) > 0) {
                for (Product product1 : basket.getBasket()) {
                    if (product1.getId() == id) {
                        product1.riseCount(count);
                        value.lowCount(count);
                        check = true;
                    }
                }

                if (!check) {
                    Product p = new Product();

                    p.setName(value.getName());
                    p.setPrice(value.getPrice());
                    p.setRank(value.getRank());
                    p.setDiscount(value.getDiscount());
                    p.setGroup(value.getGroup());
                    p.setCount(count);
                    p.setId(id);

                    basket.getBasket().add(p);
                    value.lowCount(count);
                }
            }

            else if (value.getId() == id && (value.getCount() - count) == 0) {
                for (Product product1 : basket.getBasket()) {
                    if (product1.getId() == id) {
                        product1.riseCount(count);
                        check2 = true;
                    }
                }

                if (!check2) {
                    Product p = new Product();

                    p.setName(value.getName());
                    p.setPrice(value.getPrice());
                    p.setRank(value.getRank());
                    p.setDiscount(value.getDiscount());
                    p.setGroup(value.getGroup());
                    p.setCount(count);
                    p.setId(id);

                    basket.getBasket().add(p);
                }

                deleteProduct(value);
                break;
            }
        }
    }

    public void deleteProductToTheBasket(Basket basket, int id, int count) {
        boolean check = false;
        boolean check2 = false;

        for (Product value : basket.getBasket()) {
            if (value.getId() == id && (value.getCount() - count) == 0) {
                Product p = new Product();

                p.setName(value.getName());
                p.setPrice(value.getPrice());
                p.setRank(value.getRank());
                p.setDiscount(value.getDiscount());
                p.setGroup(value.getGroup());
                p.setCount(count);
                p.setId(id);

                for (Product product : products) {
                    if (product.getId() == id) {
                        product.riseCount(count);
                        check = true;
                    }
                }

                if (!check) {
                    products.add(p);
                }

                basket.getBasket().remove(value);
                break;
            }

            else if (value.getId() == id && (value.getCount() - count) > 0) {
                for (Product product : products) {
                    if (product.getId() == id) {
                        product.riseCount(count);
                        check2 = true;
                    }
                }

                if (!check2) {
                    Product p = new Product();

                    p.setName(value.getName());
                    p.setPrice(value.getPrice());
                    p.setRank(value.getRank());
                    p.setDiscount(value.getDiscount());
                    p.setGroup(value.getGroup());
                    p.setCount(count);
                    p.setId(id);

                    products.add(p);
                }

                value.lowCount(count);
            }
        }
    }

    public boolean checkEqualCountOfProducts(int id, int count) {
        for (Product value : products) {
            if (value.getId() == id && (value.getCount() - count) >= 0) {
                return true;
            }
        }

        return false;
    }

    public void changeDeliveryOnOff() {
        if (delivery) {
            delivery = false;
        }
        else {
            delivery = true;
        }
    }
}