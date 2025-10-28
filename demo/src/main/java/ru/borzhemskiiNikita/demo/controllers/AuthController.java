package ru.borzhemskiiNikita.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.borzhemskiiNikita.demo.services.UserService;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/registration")
    public String registration(@RequestParam("login") String login, @RequestParam("password") String password) {
        if (!userService.registrationProcedure(login, password)) {
            return "accessDeniedPage";
        }
        return "goodJobPage";
    }

    @GetMapping("/getRegPage")
    public String getReg() {
        return "registrationPage";
    }

    @PostMapping("/authMethod")
    public String authMethod(@RequestParam("login") String login, @RequestParam("password") String password) {
        String checkAuth = userService.authorisationProcedure(login, password);

        if (checkAuth.equals("admin")) {
            return "redirect:/adminConsole";
        }
        else if (checkAuth.equals("user")) {
            return "redirect:/userConsole";
        }

        return "accessDeniedPage";
    }

    @GetMapping("/onlineShop")
    public String auth() {
        return "authPage";
    }
}
