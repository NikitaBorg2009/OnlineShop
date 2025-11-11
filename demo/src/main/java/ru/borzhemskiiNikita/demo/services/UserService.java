package ru.borzhemskiiNikita.demo.services;

import ru.borzhemskiiNikita.demo.models.Basket;
import ru.borzhemskiiNikita.demo.models.Category;

/**
 * Интерфейс, реализующий основной функционал для покупателя
 */
public interface UserService {

    /**
     * Метод изменяет пароль пользователя
     *
     * @param password пароль пользователя
     */
    void changePassword(String password);

    /**
     * Метод изменяет логин пользователя
     *
     * @param login логин пользователя
     */
    void changeLogin(String login);

    /**
     * Метод пополняет баланс кошелька пользователя
     *
     * @param money сумма на которую нужно увеличить баланс кошелька пользователя
     */
    void addBalance(int money);

    /**
     * Метод добавляет продукт в корзину покупателя
     *
     * @param id    код товара
     * @param count кол-во товаров
     */
    void addProductBasket(int id, int count);

    /**
     * Метод удаляет товар из корзины покупателя
     *
     * @param id    код товара
     * @param count кол-во товаров
     */
    void deleteProductInTheShop(int id, int count);

    /**
     * Метод очищает полностью корзину
     */
    void clearBasket();

    /**
     * Метод возвращает сумму всех товаров в корзине покупателя
     *
     * @return сумма всех товаров корзины
     */
    int changeMoneyUser();

    /**
     * Метод возвращает баланс кошелька пользователя
     *
     * @return баланс кошелька пользователя
     */
    int getUsersPocket();

    /**
     * Метод возвращает информацию о корзине пользователя
     *
     * @return информация о корзине пользователя в виде {@link Basket}
     */
    Basket getBasket();

    /**
     * Метод возвращает информацию об онлайн магазине
     *
     * @return информацию об онлайн магазине в виде {@link Category}
     */
    Category getCategory();

    /**
     * Метод возвращает выбор пользователя (Да/Нет)
     *
     * @param choice выбор пользователя
     * @return True, если выбор пользователя равняет "Yes"
     */
    boolean checkChoice(String choice);

    /**
     * Метод проверяет, может ли пользователь купить себе доставку или нет
     *
     * @return True, если он может купить доставку, иначе False
     */
    boolean checkPayDelivery();

    /**
     * Метод проверяет, была ли оплачена корзина и хватило ли денег или нет
     *
     * @return True, если корзина была куплена, иначе False
     */
    boolean checkBuyBasketWithMoney();

    /**
     * Метод возвращает есть ли доставка у магазина или нет
     *
     * @return True, если есть, иначе Flase
     */
    boolean isDeliveryCategory();

    /**
     * Метод проверяет, равняется ли код товара и кол-во его с товаром из магазина или нет
     *
     * @param id    код товара
     * @param count кол-во товара
     * @return True, если товар действительно равняется, иначе False
     */
    boolean checkEqualCountOfProductsCategory(int id, int count);

    /**
     * Метод проверяет, равняется ли баланс кошелька -1 или нет
     *
     * @return True, если да, иначе False
     */
    boolean checkUsersPocketCount();

    /**
     * Метод проверяет, есть ли такой товар в корзине, которому соответствует код данного товара и его кол-во
     *
     * @param id    код товара
     * @param count кол-во товаров
     * @return True, если соответствует, иначе False
     */
    boolean checkEqualBasket(int id, int count);


//--------------------------Authorisation methods--------------------------

    /**
     * Метод проверяет, прошёл ли логин и пароль пользователя авторизацию или нет
     *
     * @param login    логин пользователя
     * @param password пароль пользователя
     * @return True, если прошёл, иначе False
     */
    boolean registrationProcedure(String login, String password);

    /**
     * Метод проверяет, в какой аккаунт вошёл пользователь: администратор, покупатель, либо не вошёл, а вывело ошибку
     *
     * @param login    логин пользователя
     * @param password пароль пользователя
     * @return представление страницы: user - покупатель, admin - администратор или denied - ошибка входа (нет такого
     * аккаунта)
     */
    String authorisationProcedure(String login, String password);
}
