package ru.borzhemskiiNikita.demo.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Класс, который содержит информацию о продукте
 */
@Getter
@Setter
@Component
public class Product {
    private String name;
    private int price;
    private int rank;
    private int discount;
    private String group;
    private int count;
    private int id;

    /**
     * Метод увеличивает кол-во продукта на указанное число
     *
     * @param count число на которое нужно увеличить кол-во продукта
     */
    public void riseCount(int count) {
        this.count += count;
    }

    /**
     * Метод уменьшает кол-во продукта на указанное число
     *
     * @param count число на которое нужно уменьшить кол-во продукта
     */
    public void lowCount(int count) {
        this.count -= count;
    }
}
