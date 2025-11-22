package ru.borzhemskiiNikita.demo.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, содержащий информацию об онлайн магазине
 */
@Getter
@Setter
@Component
public class Category {
    private List<Product> products = new ArrayList<>();
    private boolean delivery;
    private int deliveryPrice;

    /**
     * Метод, проверяющий наличие конкретного продукта в категории магазина
     *
     * @param p конкретный продукт
     * @return True, если присутствует продукт в магазине, иначе False
     */
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

    /**
     * Метод изменяет информацию о продукте в онлайн магазине
     *
     * @param p        старый продукт
     * @param product2 новый продукт
     */
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

    /**
     * Метод удаляет конкретный товар из онлайн магазина
     *
     * @param p конкретный продукт
     */
    public void deleteProduct(Product p) {
        products.removeIf(value -> value.getName().equals(p.getName()) && value.getPrice() == p.getPrice() &&
                value.getRank() == p.getRank() && value.getDiscount() == p.getDiscount() &&
                value.getGroup().equals(p.getGroup()) &&
                value.getId() == p.getId());
    }

    /**
     * Метод увеличивает кол-во продуктов
     *
     * @param p конкретный продукт
     */
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

    /**
     * Метод добавляет продукт в корзину пользователя
     *
     * @param basket корзина пользователя
     * @param id     код товара
     * @param count  кол-во продуктов
     */
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
            } else if (value.getId() == id && (value.getCount() - count) == 0) {
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

    /**
     * Метод удаляет продукт из корзины пользователя
     *
     * @param basket корзина пользователя
     * @param id     код товара
     * @param count  кол-во товаров
     */
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
            } else if (value.getId() == id && (value.getCount() - count) > 0) {
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

    /**
     * Метод проверяет есть ли такой продукт в магазине у которого совпадает код товара и кол-во его
     *
     * @param id    код товара
     * @param count кол-во товаров
     * @return True, если есть такой товар, иначе False
     */
    public boolean checkEqualCountOfProducts(int id, int count) {
        for (Product value : products) {
            if (value.getId() == id && (value.getCount() - count) >= 0) {
                return true;
            }
        }

        return false;
    }

    /**
     * Метод включает либо выключает доставку онлайн магазина
     */
    public void changeDeliveryOnOff() {
        if (delivery) {
            delivery = false;
        } else {
            delivery = true;
        }
    }
}
