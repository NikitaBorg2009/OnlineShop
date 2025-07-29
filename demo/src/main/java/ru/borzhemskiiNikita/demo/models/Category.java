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
                    value.getGroup().equals(p.getGroup()) && value.getCount() == p.getCount() &&
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
                value.getGroup().equals(p.getGroup()) && value.getCount() == p.getCount() &&
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
}