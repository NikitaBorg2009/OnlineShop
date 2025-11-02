package ru.borzhemskiiNikita.demo.services;

import ru.borzhemskiiNikita.demo.models.Product;

import java.util.List;

/**
 * Интерфейс, реализующий основной функционал для фильтров товаров
 */
public interface FilterService {

    /**
     * Метод фильтрует товары у которых цена находится в диапазоне минимальной и максимальной цены пользователя
     *
     * @param minPrice минимальная цена для товаров
     * @param maxPrice максимальная цена для товаров
     * @return список товаров в виде {@link List<Product>}, которые прошли фильтр по цене
     */
    List<Product> priceFilter(int minPrice, int maxPrice);

    /**
     * Метод фильтрует товары у которых рейтинг находится в диапазоне минимального и максимального рейтинга пользователя
     *
     * @param minRank минимальный рейтинг для товаров
     * @param maxRank максимальный рейтинг для товаров
     * @return список товаров в виде {@link List<Product>}, которые прошли фильтр по рейтингу
     */
    List<Product> rankFilter(int minRank, int maxRank);

    /**
     * Метод фильтрует товары у которых начальные буквы названия товара такие же как и ввёл сам пользователь
     *
     * @param name имя товара или начальные буквы названия его
     * @return список товаров в виде {@link List<Product>}, которые прошли фильтр по названию
     */
    List<Product> nameFilter(String name);

    /**
     * Метод возвращает список товаров для просмотра без фильтров
     *
     * @return список товаров в виде {@link List<Product>}
     */
    List<Product> getProducts();

    /**
     * Метод снимает все фильтры с товаров
     */
    void filterConsoleUpdate();
}
