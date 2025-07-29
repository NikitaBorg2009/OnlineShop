package ru.borzhemskiiNikita.demo.models;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Component
public class UsersArray {
    private String openedLogin = StringUtils.EMPTY;
    private String openedPassword = StringUtils.EMPTY;
    private List<User> allUsers = new ArrayList<>();

    public void changeUserLogin(User user) {
        for (User allUser : allUsers) {
            if (allUser.getLogin().equals(openedLogin) &&
                    allUser.getPassword().equals(openedPassword)) {
                allUser.setLogin(user.getLogin());
            }
        }
        openedLogin = user.getLogin();
    }

    public void changeUserPassword(User user) {
        for (User allUser : allUsers) {
            if (allUser.getLogin().equals(openedLogin) &&
                    allUser.getPassword().equals(openedPassword)) {
                allUser.setPassword(user.getPassword());
            }
        }
        openedPassword = user.getPassword();
    }

    public void addMoneyUser(int money) {
        for (User allUser : allUsers) {
            if (allUser.getLogin().equals(openedLogin) &&
                    allUser.getPassword().equals(openedPassword)) {
                allUser.addMoneyBankCard(money);
            }
        }
    }

    public int getUserPocket() {
        for (User allUser : allUsers) {
            if (allUser.getLogin().equals(openedLogin) &&
                    allUser.getPassword().equals(openedPassword)) {
                return allUser.getPocket();
            }
        }

        return -1;
    }
}