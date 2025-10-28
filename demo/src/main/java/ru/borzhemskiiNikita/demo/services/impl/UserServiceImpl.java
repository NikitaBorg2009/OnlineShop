package ru.borzhemskiiNikita.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.borzhemskiiNikita.demo.models.*;
import ru.borzhemskiiNikita.demo.services.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersArray users;
    @Autowired
    private Category category;
    @Autowired
    private Basket basket;
    @Autowired
    private Admin admin;

//--------------------------Users methods--------------------------


    @Override
    public boolean checkEqualBasket(int id, int count) {
        for (Product value : basket.getBasket()) {
            if (value.getId() == id && (value.getCount() - count) >= 0) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean checkBuyBasketWithMoney() {
        return users.buyBasketWithMoney(changeMoneyUser());
    }

    @Override
    public void changePassword(String password) {
        User user1 = new User();
        user1.setPassword(password);

        users.changeUserPassword(user1);
    }

    @Override
    public void changeLogin(String login) {
        User user = new User();
        user.setLogin(login);

        users.changeUserLogin(user);
    }

    @Override
    public void addProductBasket(int id, int count) {
        category.addProductToTheBasket(basket, id, count);
    }

    @Override
    public void addBalance(int money) {
        users.addMoneyUser(money);
    }

    @Override
    public void deleteProductInTheShop(int id, int count) {
        category.deleteProductToTheBasket(basket, id, count);
    }

    @Override
    public int changeMoneyUser() {
        int money1 = 0;

        for (Product p1 : basket.getBasket()) {
            money1 += p1.getPrice()*p1.getCount();
        }

        return money1;
    }

    @Override
    public void clearBasket() {
        basket.getBasket().clear();
    }

    @Override
    public Basket getBasket() {
        return basket;
    }

    @Override
    public boolean checkChoice(String choice) {
        return choice.equals("yes");
    }

    @Override
    public boolean checkPayDelivery() {
        return users.payDelivery(category.getDeliveryPrice());
    }

    @Override
    public Category getCategory() {
        return category;
    }

    @Override
    public boolean isDeliveryCategory() {
        return category.isDelivery();
    }

    @Override
    public boolean checkEqualCountOfProductsCategory(int id, int count) {
        return category.checkEqualCountOfProducts(id, count);
    }

    @Override
    public int getUsersPocket() {
        return users.getUserPocket();
    }

    @Override
    public boolean checkUsersPocketCount() {
        return users.getUserPocket() == -1;
    }


//--------------------------Authorisation Methods--------------------------


    @Override
    public boolean registrationProcedure(String login, String password) {
        for (int i = 0; i < users.getAllUsers().size(); i++) {
            if (users.getAllUsers().get(i).getLogin().equals(login) &&
                    users.getAllUsers().get(i).getPassword().equals(password)) {
                return false;
            }
        }

        User user1 = new User();

        user1.setPassword(password);
        user1.setLogin(login);

        users.getAllUsers().add(user1);

        return true;
    }

    @Override
    public String authorisationProcedure(String login, String password) {
        if (login.equals(admin.getLogin()) && password.equals(admin.getPassword())) {
            users.setOpenedLogin(login);
            users.setOpenedPassword(password);

            User user1 = new User();

            user1.setLogin(login);
            user1.setPassword(password);

            users.getAllUsers().add(user1);

            return "admin";
        }

        for (int i = 0; i < users.getAllUsers().size(); ++i) {
            if (users.getAllUsers().get(i).getLogin().equals(login) &&
                    users.getAllUsers().get(i).getPassword().equals(password)) {

                users.setOpenedLogin(login);
                users.setOpenedPassword(password);

                User user1 = new User();

                user1.setLogin(login);
                user1.setPassword(password);

                users.getAllUsers().add(user1);

                return "user";
            }
        }

        return "denied";
    }
}
