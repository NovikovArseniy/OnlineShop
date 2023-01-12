package ru.novikov.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.novikov.shop.model.Cart;
import ru.novikov.shop.model.CartToProducts;
import ru.novikov.shop.model.Product;

import java.util.List;

public interface CartToProductsRepository extends JpaRepository<CartToProducts, Long> {
    void deleteByCart(Cart cart);

    //@Query("SELECT amount FROM orders_products WHERE user_id=?1 AND productid=?2")
    //int getAmountByUserIdAndProductId(Long userId, Long productId);

    CartToProducts getByCartAndProduct(Cart cart, Product product);

    List<CartToProducts> getByCart(Cart cart);
}
