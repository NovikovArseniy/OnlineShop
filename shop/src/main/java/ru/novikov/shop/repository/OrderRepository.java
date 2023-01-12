package ru.novikov.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.novikov.shop.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

    //Order getOrderByUser(User user);
}
