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

    int changeMoneyUser();
    int getUsersPocket();

    Basket getBasket();
    Category getCategory();

    boolean checkChoice(String choice);
    boolean checkPayDelivery();
    boolean checkBuyBasketWithMoney();
    boolean isDeliveryCategory();
    boolean checkEqualCountOfProductsCategory(int id, int count);
    boolean checkUsersPocketCount();
    boolean checkEqualBasket(int id, int count);


//--------------------------Authorisation methods--------------------------


    boolean registrationProcedure(String login, String password);
    String authorisationProcedure(String login, String password);
}
