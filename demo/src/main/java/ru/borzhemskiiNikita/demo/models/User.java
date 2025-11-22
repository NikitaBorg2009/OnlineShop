package ru.borzhemskiiNikita.demo.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Класс, содержащий основную информацию об пользователе
 */
@Setter
@Getter
@Component
public class User {
    private String login;
    private String password;
    private Basket basket;
    private int pocket;

    /**
     * Метод увеличивает баланс кошелька на определённую сумму
     *
     * @param money определённая сумма на которую надо увеличить баланс кошелька
     */
    public void addMoneyBankCard(int money) {
        pocket += money;
    }

    /**
     * Метод уменьшает баланс кошелька на определённую сумму
     *
     * @param money определённая сумма на которую надо уменьшить баланс кошелька
     */
    public void deleteMoneyBankCard(int money) {
        pocket -= money;
    }
}
