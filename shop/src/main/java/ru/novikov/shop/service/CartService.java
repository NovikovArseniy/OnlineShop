package ru.novikov.shop.service;

import ru.novikov.shop.model.Cart;
import ru.novikov.shop.model.Product;

import java.util.Map;

public interface CartService {

    Cart addCart(Cart cart);
    Cart getById(int id);
    Cart addProduct(Product product);
    Cart removeProduct(Product product);

    Map<Product, Integer> getCartMap(Cart cart);

    Cart getCurrent();
    void clearCart(Cart cart);
}
