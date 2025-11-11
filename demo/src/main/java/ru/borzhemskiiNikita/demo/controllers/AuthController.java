package ru.borzhemskiiNikita.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.borzhemskiiNikita.demo.services.UserService;

/**
 * Класс, реализующий запросы связанные с авторизацией аккаунта в онлайн магазине
 */
@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    /**
     * Метод регистрирует и создаёт новый аккаунт пользователя
     *
     * @param login    логин пользователя
     * @param password пароль пользователя
     * @return представление страницы goodJobPage, если аккаунт создан правильно и не такой же, как у других, иначе
     * представление страницы accessDeniedPage
     */
    @PostMapping("/registration")
    public String registration(@RequestParam("login") String login, @RequestParam("password") String password) {
        if (!userService.registrationProcedure(login, password)) {
            return "accessDeniedPage";
        }
        return "goodJobPage";
    }

    /**
     * Метод открывает страницу регистрации аккаунта пользователя
     *
     * @return представление страницы registrationPage
     */
    @GetMapping("/getRegPage")
    public String getReg() {
        return "registrationPage";
    }

    /**
     * Метод проверяет аккаунт пользователя, кем он является: админом или покупателем. В случае неправильного ввода
     * логина или пароля, выводит ошибку
     *
     * @param login    логин пользователя
     * @param password пароль пользователя
     * @return представление страницы accessDeniedPage. Для покупателя перенаправляет в URL /userConsole, для
     * администратора перенаправляет в URL /adminConsole
     */
    @PostMapping("/authMethod")
    public String authMethod(@RequestParam("login") String login, @RequestParam("password") String password) {
        String checkAuth = userService.authorisationProcedure(login, password);

        if (checkAuth.equals("admin")) {
            return "redirect:/adminConsole";
        } else if (checkAuth.equals("user")) {
            return "redirect:/userConsole";
        }

        return "accessDeniedPage";
    }

    /**
     * Метод перенаправляет пользователя на страницу авторизации аккаунта
     *
     * @return представление страницы AuthPage
     */
    @GetMapping("/onlineShop")
    public String auth() {
        return "authPage";
    }
}
