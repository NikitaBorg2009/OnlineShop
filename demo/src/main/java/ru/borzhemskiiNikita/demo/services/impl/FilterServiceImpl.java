package ru.borzhemskiiNikita.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.borzhemskiiNikita.demo.models.Category;
import ru.borzhemskiiNikita.demo.models.Product;
import ru.borzhemskiiNikita.demo.services.FilterService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class FilterServiceImpl implements FilterService {
    @Autowired
    private Category category;
    private List<Product> products = new ArrayList<>();

    @Override
    public List<Product> priceFilter(int minPrice, int maxPrice) {
        return products.stream().filter(product ->
                product.getPrice()>=minPrice && product.getPrice()<=maxPrice).toList();
    }

    @Override
    public List<Product> rankFilter(int minRank, int maxRank) {
        return products.stream().filter(product ->
                product.getRank()>=minRank && product.getRank()<=maxRank).toList();
    }

    @Override
    public List<Product> nameFilter(String name) {
        return products.stream().filter((product) -> {
            StringBuilder help = new StringBuilder();

            for (int i = 0; i < name.length(); i++) {
                help.append(product.getName().charAt(i));
            }

            return help.toString().equals(name);
        }).toList();
    }

    @Override
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public void filterConsoleUpdate() {
        products = new ArrayList<>();
        products.addAll(category.getProducts());
    }
}
