package ru.borzhemskiiNikita.demo.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Класс, который содержит информацию об администраторе
 */
@Component
@Setter
@Getter
public class Admin {
    private String login = "admin";
    private String password = "1234";
}
