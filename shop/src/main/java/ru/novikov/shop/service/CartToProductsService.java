package ru.novikov.shop.service;

import ru.novikov.shop.model.Cart;
import ru.novikov.shop.model.CartToProducts;
import ru.novikov.shop.model.Product;
import java.util.Map;
import java.util.Set;

public interface CartToProductsService {

    CartToProducts addCartToProducts(CartToProducts cartToProducts);
    Integer deleteByCart(Cart cart);


    CartToProducts getByCartAndProduct(Cart cart, Product product);

    Map<Product, Integer> getCartMap(Cart cart);

    Set<CartToProducts> getByCart(Cart cart);

    Integer deleteByCartAndProduct(Cart cart, Product product);
}
