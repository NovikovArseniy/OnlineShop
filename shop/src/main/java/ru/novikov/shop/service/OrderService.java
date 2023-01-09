package ru.novikov.shop.service;

import ru.novikov.shop.model.Order;
import ru.novikov.shop.model.Product;

import java.util.List;

public interface OrderService {
    Order addOrder(Order order);
    void delete(int id);
    Order getByName(String name);
    Order getById(int id);
    //List<Product> getAll();
}
