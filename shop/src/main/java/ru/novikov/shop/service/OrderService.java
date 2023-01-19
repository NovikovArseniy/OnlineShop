package ru.novikov.shop.service;

import ru.novikov.shop.model.Order;
import ru.novikov.shop.model.User;

import java.util.List;

public interface OrderService {
    Order addOrder(Order order);
    void delete(Long id);
    Order getById(Long id);

    List<Order> getAll();
    List<Order> getByUser(User user);
}