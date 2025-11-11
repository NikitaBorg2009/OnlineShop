package ru.borzhemskiiNikita.demo.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, содержащий информацию об корзины покупателя
 */
@Component
@Setter
@Getter
public class Basket {
    private List<Product> basket = new ArrayList<>();
}
