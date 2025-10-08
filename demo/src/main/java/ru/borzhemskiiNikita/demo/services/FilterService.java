package ru.borzhemskiiNikita.demo.services;

import ru.borzhemskiiNikita.demo.models.Product;

import java.util.List;

public interface FilterService {
    List<Product> priceFilter(int minPrice, int maxPrice);
    List<Product> rankFilter(int minRank, int maxRank);
    List<Product> nameFilter(String name);

    List<Product> getProducts();

    void filterConsoleUpdate();
}