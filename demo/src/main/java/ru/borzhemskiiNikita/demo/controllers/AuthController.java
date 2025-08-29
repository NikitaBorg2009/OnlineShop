package ru.borzhemskiiNikita.demo.controllers;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.borzhemskiiNikita.demo.models.Admin;
import ru.borzhemskiiNikita.demo.models.User;
import ru.borzhemskiiNikita.demo.models.UsersArray;

@Controller
public class AuthController {
    @Autowired
    private Admin admin;
    @Autowired
    private UsersArray users;

    @PostMapping("/registration")
    public String registration(@RequestParam("login") String login, @RequestParam("password") String password) {
        for (int i = 0; i < users.getAllUsers().size(); i++) {
            if (users.getAllUsers().get(i).getLogin().equals(login) &&
                    users.getAllUsers().get(i).getPassword().equals(password)) {
                return "accessDeniedPage";
            }
        }

        User user1 = new User();

        user1.setPassword(password);
        user1.setLogin(login);

        users.getAllUsers().add(user1);

        return "goodJobPage";
    }

    @GetMapping("/getRegPage")
    public String getReg() {
        return "registrationPage";
    }

    @PostMapping("/authMethod")
    public String authMethod(@RequestParam("login") String login, @RequestParam("password") String password) {
        if (login.equals(admin.getLogin()) && password.equals(admin.getPassword())) {
            users.setOpenedLogin(login);
            users.setOpenedPassword(password);

            User user1 = new User();

            user1.setLogin(login);
            user1.setPassword(password);

            users.getAllUsers().add(user1);

            return "redirect:/adminConsole";
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

                return "redirect:/userConsole";
            }
        }

        return "accessDeniedPage";
    }

    @GetMapping("/onlineShop")
    public String auth() {
        return "authPage";
    }
}
