package ru.borzhemskiiNikita.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Setter
@Getter
@Component
public class User {
    private String login;
    private String password;
    private Basket basket;
    private int pocket;

    public void addMoneyBankCard(int money) {
        pocket += money;
    }

    public void deleteMoneyBankCard(int money) {
        pocket -= money;
    }
}