package ru.borzhemskiiNikita.demo.services;
import ru.borzhemskiiNikita.demo.models.Product;

public interface AdminService {
    boolean changeProduct(Product oldProduct, String name, int price, int rank, int discount, String group,
                          int count, int id);

    boolean deleteProduct(Product product);

    boolean createNewProduct(Product product);

    boolean switchOnDelivery();

    boolean changeDeliveryPrice(int money);

    void switchOnDeliveryPrice(String choice);

    Product getProduct();
}
