package ru.borzhemskiiNikita.demo.models;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, содержащий общую информацию обо всех пользователей онлайн магазина
 */
@Getter
@Setter
@Component
public class UsersArray {
    private String openedLogin = StringUtils.EMPTY;
    private String openedPassword = StringUtils.EMPTY;
    private List<User> allUsers = new ArrayList<>();

    /**
     * Метод изменяет логин пользователя
     *
     * @param user пользователя в виде {@link User}
     */
    public void changeUserLogin(User user) {
        for (User allUser : allUsers) {
            if (allUser.getLogin().equals(openedLogin) &&
                    allUser.getPassword().equals(openedPassword)) {
                allUser.setLogin(user.getLogin());
            }
        }
        openedLogin = user.getLogin();
    }

    /**
     * Метод изменяет пароль пользователя
     *
     * @param user пользователя в виде {@link User}
     */
    public void changeUserPassword(User user) {
        for (User allUser : allUsers) {
            if (allUser.getLogin().equals(openedLogin) &&
                    allUser.getPassword().equals(openedPassword)) {
                allUser.setPassword(user.getPassword());
            }
        }
        openedPassword = user.getPassword();
    }

    /**
     * Метод пополняет баланс кошелька пользователя на определённую сумму
     *
     * @param money определённая сумма на которую нужно увеличить баланс кошелька
     */
    public void addMoneyUser(int money) {
        for (User allUser : allUsers) {
            if (allUser.getLogin().equals(openedLogin) &&
                    allUser.getPassword().equals(openedPassword)) {
                allUser.addMoneyBankCard(money);
            }
        }
    }

    /**
     * Метод возвращает баланс кошелька
     *
     * @return баланс кошелька если пароль и логин равняется паролю и логину открытого аккаунта, иначе выводится "-1"
     */
    public int getUserPocket() {
        for (User allUser : allUsers) {
            if (allUser.getLogin().equals(openedLogin) &&
                    allUser.getPassword().equals(openedPassword)) {
                return allUser.getPocket();
            }
        }

        return -1;
    }

    /**
     * Метод позволяет оплатить корзину пользователя
     *
     * @param money сумма всех товаров в корзине покупателя
     * @return True, если корзина оплачена, иначе False, если не хватает средств
     */
    public boolean buyBasketWithMoney(int money) {
        for (User allUser : allUsers) {
            if (allUser.getLogin().equals(openedLogin) &&
                    allUser.getPassword().equals(openedPassword)) {
                if (money <= allUser.getPocket()) {
                    allUser.deleteMoneyBankCard(money);
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Метод позволяет оплатить доставку оплаченных продуктов
     *
     * @param deliveryPrice цена доставки
     * @return True, если доставка была успешно оплачена, иначе False, если не получилось из-за нехватки средств на
     * балансе кошелька пользователя
     */
    public boolean payDelivery(int deliveryPrice) {
        for (User allUser : allUsers) {
            if (allUser.getLogin().equals(openedLogin) &&
                    allUser.getPassword().equals(openedPassword)) {
                if (deliveryPrice <= allUser.getPocket()) {
                    allUser.deleteMoneyBankCard(deliveryPrice);
                    return true;
                }

                return false;
            }
        }

        return false;
    }
}
