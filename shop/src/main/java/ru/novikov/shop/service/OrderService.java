package ru.novikov.shop.service;

import ru.novikov.shop.model.Order;

public interface OrderService {
    Order addOrder(Order order);
    void delete(Long id);
    Order getById(Long id);
}