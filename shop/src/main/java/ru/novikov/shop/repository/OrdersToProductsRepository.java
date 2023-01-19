package ru.novikov.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.novikov.shop.model.Order;
import ru.novikov.shop.model.OrdersToProducts;

import java.util.List;

public interface OrdersToProductsRepository extends JpaRepository<OrdersToProducts, Long> {

    void deleteByOrder(Order order);
}
