package ru.borzhemskiiNikita.demo.services;

import ru.borzhemskiiNikita.demo.models.Basket;
import ru.borzhemskiiNikita.demo.models.Category;

public interface UserService {
    void changePassword(String password);
    void changeLogin(String login);
    void addBalance(int money);
    void addProductBasket(int id, int count);
    void deleteProductInTheShop(int id, int count);
    void clearBasket();

    int changeMoney1();
    int getUsersPocket();

    Basket returnBasket();
    Category returnCategory();

    boolean checkChoice(String choice);
    boolean checkPayDelivery();
    boolean checkBuyBasketWithMoney();
    boolean isDeliveryCategory();
    boolean checkEqualCountOfProductsCategory(int id, int count);
    boolean checkUsersPocketCount();
}
