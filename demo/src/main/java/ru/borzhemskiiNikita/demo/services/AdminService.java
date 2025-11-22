package ru.borzhemskiiNikita.demo.services;

import ru.borzhemskiiNikita.demo.models.Product;

/**
 * Интерфейс, реализующий основной функционал для администратора
 */
public interface AdminService {

    /**
     * Метод изменяет информацию об продукте в онлайн магазине
     *
     * @param oldProduct информация о старом продукте в виде {@link Product}
     * @param name       имя товара
     * @param price      цена товара
     * @param rank       рейтинг товара
     * @param discount   размер скидки
     * @param group      категория или группа продукта
     * @param count      кол-во продуктов
     * @param id         код продукта
     * @return True, если товар прошёл все проверки при изменении, иначе False
     */
    boolean changeProduct(Product oldProduct, String name, int price, int rank, int discount, String group,
                          int count, int id);

    /**
     * Метод удаляет полностью товар из онлайн магазина
     *
     * @param product информация о продукте {@link Product}
     * @return True, если товар прошёл проверки и удалился, иначе False, если такого товара нет в магазине
     */
    boolean deleteProduct(Product product);

    /**
     * Метод создаёт новый товар и добавляет его в онлайн магазин, либо увеличивает кол-во товаров, если такой товар
     * уже есть в магазине
     *
     * @param product информация о продукте {@link Product}
     * @return True, если товар прошёл проверки и добавился в магазин, иначе False, если товар неправильно создали
     */
    boolean createNewProduct(Product product);

    /**
     * Метод возвращает информацию, включёна ли доставка или нет
     *
     * @return информацию о доставке
     */
    boolean switchOnDelivery();

    /**
     * Метод изменяет цену доставки онлайн магазина
     *
     * @param money новая цена доставки
     * @return False, если новая цена ниже 0, иначе True если прошла все проверки
     */
    boolean changeDeliveryPrice(int money);

    /**
     * Метод добавляет цену доставки любо убирает (делает бесплатной)
     *
     * @param choice выбор администратора
     */
    void switchOnDeliveryPrice(String choice);

    /**
     * Метод возвращает информацию о продукте
     *
     * @return информация о продукте в виде {@link Product}
     */
    Product getProduct();
}
