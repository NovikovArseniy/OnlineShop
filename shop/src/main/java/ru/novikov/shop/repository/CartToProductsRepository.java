package ru.novikov.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.novikov.shop.model.Cart;
import ru.novikov.shop.model.CartToProducts;
import ru.novikov.shop.model.Product;

import java.util.Set;

public interface CartToProductsRepository extends JpaRepository<CartToProducts, Long> {

    Integer removeByCart(Cart cart);

    CartToProducts getByCartAndProduct(Cart cart, Product product);

    Set<CartToProducts> getByCart(Cart cart);

    Integer removeByCartAndProduct(Cart cart, Product product);
}
