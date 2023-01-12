package ru.novikov.shop.service;

import ru.novikov.shop.model.Order;

public interface OrderService {
    Order addOrder(Order order);
    void delete(Long id);
    //Order getByName(String name);
    Order getById(Long id);
    //List<Product> getAll();
}