package ru.borzhemskiiNikita.demo.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Setter
@Getter
public class Basket {
    private List<Product> basket = new ArrayList<>();
}
