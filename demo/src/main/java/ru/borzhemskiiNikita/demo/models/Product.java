package ru.borzhemskiiNikita.demo.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

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

    public void riseCount(int count) {
        this.count += count;
    }

    public void lowCount(int count) {
        this.count -= count;
    }
}