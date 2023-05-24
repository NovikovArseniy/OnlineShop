package ru.novikov.shop.service;

import ru.novikov.shop.model.Cart;
import ru.novikov.shop.model.Product;
import ru.novikov.shop.model.User;

import java.util.Map;

public interface CartService {

    Cart addCart(Cart cart);
    Cart getById(Long id);
    Cart addProduct(Product product, User user);
    Cart removeProduct(Product product, User user);

    Map<Product, Integer> getCartMap(Cart cart);

    Cart getCurrent(User user);
    void clearCart(User user);
}
