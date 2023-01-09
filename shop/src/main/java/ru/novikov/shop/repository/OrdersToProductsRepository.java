package ru.novikov.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.novikov.shop.model.Order;
import ru.novikov.shop.model.OrdersToProducts;

public interface OrdersToProductsRepository extends JpaRepository<OrdersToProducts, Integer> {
    void deleteByOrder(Order order);
}
