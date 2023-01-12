package ru.novikov.shop.service;

import ru.novikov.shop.model.Cart;
import ru.novikov.shop.model.CartToProducts;
import ru.novikov.shop.model.Product;

import java.util.List;
import java.util.Map;

public interface CartToProductsService {

    CartToProducts addCartToProducts(CartToProducts cartToProducts);
    void deleteByCart(Cart cart);


    CartToProducts getByCartAndProduct(Cart cart, Product product);

    Map<Product, Integer> getCartMap(Cart cart);

    List<CartToProducts> getByCart(Cart cart);
}
